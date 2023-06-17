$('#addBookForm').submit(function(event) {
    event.preventDefault(); // Prevent form submission

    var query = $('input[name="query"]').val();
    addBook(query);
});

function addBook(query) {
    $('#addBookForm').submit(function(event) {
        event.preventDefault(); // Prevent form submission

        var query = $('input[name="query"]').val();
        $.ajax({
            url: '/addBook',
            method: 'POST',
            data: { query: query },
            success: function(data) {
                displaySearchResults(data);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
}

function editBook() {
    // Logic to handle editing a book
    // For example, display a form to edit book details
}

function deleteBook() {
    // Logic to handle deleting a book
    // For example, display a confirmation dialog and delete the book upon confirmation
}
