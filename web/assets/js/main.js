let allIncomes = [];
let currentSearch = '';
let searchEnabled = false;

function getIncomeFor(doc) {
  try {
    if (typeof doc !== 'undefined' && doc.length > 0) {
      for (const income of allIncomes) {
        try {
          if (income.doc == doc) {
            return income.income;
          }
        } catch (e) {
        }
      }
      return 0;
    }
    return 0;
  } catch (e) {
    return 0;
  }
}

function searchPerson() {
  if (!searchEnabled) return;
  const idInput = document.getElementById('id-input');
  if (idInput) {
    searchEnabled = false;
    idInput.classList.add('is-disabled');
    idInput.disabled = true;
    const searchBtn = document.getElementById('search-btn');
    if (searchBtn) {
      searchBtn.classList.add('is-disabled');
      searchBtn.disabled = true;
    }
    currentSearch = idInput.value || '';
    idInput.value = '';
    getIncomes();
  }
}

function getPeople() {
  const actualDoc = (currentSearch || '').toString();
  currentSearch = '';
  const xhr = new XMLHttpRequest();
  xhr.open('GET', `PeopleServlet?doc=${actualDoc}`, true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        try {
          const today = new Date();
          const json = JSON.parse(xhr.responseText);
          const people = json.people || [];

          const loadingView = document.getElementById('loading');
          if (loadingView) loadingView.classList.add('is-hidden');
          const contentView = document.getElementById('content');
          if (contentView) contentView.classList.remove('is-hidden');

          if (people.length > 0) {
            const notFoundView = document.getElementById('not-found');
            if (notFoundView) notFoundView.classList.add('is-hidden');
            const tableView = document.getElementById('table');
            if (tableView) tableView.classList.remove('is-hidden');

            const tableBody = document.getElementById('results');
            tableBody.innerHTML = '';

            for (const person of people) {
              const tableRow = document.createElement('tr');
              const typeTd = document.createElement('td');
              typeTd.innerText = person.type.toUpperCase();
              tableRow.appendChild(typeTd);

              const docTd = document.createElement('td');
              docTd.innerText = person.doc;
              tableRow.appendChild(docTd);

              const nameTd = document.createElement('td');
              nameTd.innerText = person.name;
              tableRow.appendChild(nameTd);

              const surnameTd = document.createElement('td');
              surnameTd.innerText = person.surname;
              tableRow.appendChild(surnameTd);

              const years = new Date(today - new Date(person.birth)).getFullYear() - 1970;
              const ageTd = document.createElement('td');
              ageTd.innerText = years;
              tableRow.appendChild(ageTd);
              tableBody.appendChild(tableRow);

              const incomeTd = document.createElement('td');
              incomeTd.innerText = `U$${getIncomeFor(person.doc)}`;
              tableRow.appendChild(incomeTd);
            }
          } else {
            const tableView = document.getElementById('table');
            if (tableView) tableView.classList.add('is-hidden');
            const notFoundView = document.getElementById('not-found');
            if (notFoundView) notFoundView.classList.remove('is-hidden');
          }

          const idInput = document.getElementById('id-input');
          if (idInput) {
            idInput.classList.remove('is-disabled');
            idInput.disabled = false;
            const searchBtn = document.getElementById('search-btn');
            if (searchBtn) {
              searchBtn.classList.remove('is-disabled');
              searchBtn.disabled = false;
            }
            searchEnabled = true;
          }
        } catch (e) {
          console.error(e);
        }
      } else {
        const tableView = document.getElementById('table');
        if (tableView) tableView.classList.add('is-hidden');
        const notFoundView = document.getElementById('not-found');
        if (notFoundView) notFoundView.classList.remove('is-hidden');
      }
    }
  };
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send(null);
}

function getIncomes() {
  const contentView = document.getElementById('content');
  if (contentView) contentView.classList.add('is-hidden');
  const loadingView = document.getElementById('loading');
  if (loadingView) loadingView.classList.remove('is-hidden');

  const xhr = new XMLHttpRequest();
  xhr.open('GET',
    'https://api.github.com/repos/jahirfiquitiva/DistributedTransactions/contents/data/incomes.json',
    true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        try {
          const json = JSON.parse(xhr.responseText);
          const encodedFileContent = json.content || '';
          if (encodedFileContent.length > 0) {
            const decodedFileContent = decodeURIComponent(window.atob(encodedFileContent));
            const fileContent = JSON.parse(decodedFileContent);
            allIncomes = fileContent.incomes || [];
          } else {
            console.error('Empty file');
          }
          getPeople();
        } catch (e) {
          console.error(e);
          getPeople();
        }
      } else {
        getPeople();
      }
    }
  };
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.send(null);
}

getIncomes();
