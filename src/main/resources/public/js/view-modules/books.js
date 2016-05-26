angular.module('books', [])
    .component('booksComponent', {
        templateUrl: '/templates/books.html',
        controller: ['$scope', 'BooksEndpoint', 'Authority', BooksController]
    });

function BooksController($scope, BooksEndpoint, Authority) {
    
    // TODO get this stuff from the backend.
    $scope.bookTypes = ["MEMBER", "ADMIN"];

    $scope.selectedBook = {
        id: null,
        title: null,
        author: null,
        isbn: null,
        count: null
    };

    $scope.bookToDelete = null;

    function refreshBookList() {
        BooksEndpoint.getBooksUsingGET().then(function (books) {
            console.log("Retrieved books:", books.obj);
            $scope.$evalAsync(function () {
                $scope.books = books.obj;
            });
        });
    }

    this.$routerOnActivate = function (next) {
        console.log("Books controller initialized.");
        refreshBookList();
    };

    $scope.saveBook = function (email, password, bookType) {
        console.log("Saving book ", $scope.selectedBook);
        BooksEndpoint.createBookUsingPOST({book: $scope.selectedBook}).then(function () {
            console.log("Saved book.");
            refreshBookList();
            $('#edit-modal').modal('hide');
        });
    };

    $scope.editBook = function (book) {
        $scope.selectedBook = book;
        $('#edit-modal').modal('show'); // this is ugly, but I don't care.
    };

    $scope.selectBookToDelete = function (book) {
        $scope.bookToDelete = book;
        $('#delete-modal').modal('show');
    };

    $scope.deleteBook = function () {
        BooksEndpoint.deleteBookUsingDELETE({id: $scope.bookToDelete.id}).then(function() {
            console.log("Deleted book with id", $scope.bookToDelete.id);
            $('#delete-modal').modal('hide');
            refreshBookList();
        });
    };

    $scope.newBook = function () {
        $scope.selectedBook = {
            id: null,
            title: null,
            author: null,
            isbn: null,
            count: null
        };
        $('#edit-modal').modal('show');
    };

}
