<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Distributed Transactions</title>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
        <link rel="stylesheet" href="assets/css/project.css">
    </head>
    <body>
        <section class="hero is-fullheight">
            <div class="hero-body">
                <div class="container">
                    <div class="content has-text-centered">
                        <h2>Distributed Transactions</h2>
                        <br><br>
                        <div class="field has-addons has-addons-centered">
                            <div class="control">
                                <input disabled
                                       class="input"
                                       type="text"
                                       placeholder="ID"
                                       id="id-input">
                            </div>
                            <div class="control">
                                <button disabled
                                        class="button is-info"
                                        onclick="searchPerson();"
                                        id="search-btn">
                                    Search
                                </button>
                            </div>
                        </div>
                        <br>
                        <div class="has-text-centered" id="loading">
                            <br>
                            <p>Please wait</p>
                            <a class="button is-white is-loading"></a>
                            <p>Loading…</p>
                        </div>
                        <div class="columns is-multiline is-hidden" id="content">
                            <div class="column is-1"></div>
                            <div class="column">
                                <p class="is-hidden" id="not-found">Nothing found!</p>
                                <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth"
                                       id="table">
                                    <thead>
                                    <tr>
                                        <th>Document Type</th>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Surname</th>
                                        <th>Age</th>
                                        <th>Income</th>
                                    </tr>
                                    </thead>
                                    <tbody id="results">
                                    </tbody>
                                </table>
                            </div>
                            <div class="column is-1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="assets/js/main.js"></script>
    </body>
</html>
