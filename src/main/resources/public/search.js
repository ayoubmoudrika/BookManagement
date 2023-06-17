
    $('#searchForm').submit(function(event) {
        event.preventDefault(); // Prevent form submission

        var query = $('input[name="query"]').val();
        searchBooks(query);
    });

    function searchBooks(query) {
        $.ajax({
            url: '/searchBookByGenre',
            method: 'GET',
            data: { query: query },
            success: function(data) {
                displaySearchResults(data);
            },
            error: function(error) {
                console.error(error);
            }
        });
    }

    function displaySearchResults(results) {
        var container = $('#searchResults');
        container.empty(); // Clear previous results

        if (results.length === 0) {
            container.text('No results found.');
        } else {
            var ul = $('<ul>');
            results.forEach(function(book) {
                var li = $('<li>').text(book.title);
                ul.append(li);
            });
            container.append(ul);
        }
    }

