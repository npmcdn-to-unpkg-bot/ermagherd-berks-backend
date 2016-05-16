angular.module('borrowings', [])
    .component('borrowingsComponent', {
        templateUrl: '/templates/borrowings.html',
        controller: ['$scope', 'BorrowingsEndpoint', 'BooksEndpoint', 'MembersEndpoint', BorrowingsController]
    });

function BorrowingsController($scope, BorrowingsEndpoint, BooksEndpoint, MembersEndpoint) {

    $scope.selectedBorrowing = {
        id: null,
        bookId: null,
        memberId: null,
        timeOfBorrowing: null,
        deadline: null
    };

    $scope.borrowingToDelete = null;

    $scope.members = [];
    $scope.books = [];
    $scope.borrowings = [];

    $scope.getMemberById = function (memberId) {
        return $scope.members.filter(function (member) {
           return member.id == memberId;
        })[0];
    };

    $scope.getBookById = function (bookId) {
        return $scope.books.filter(function (book) {
            return book.id == bookId;
        })[0];
    };

    function refreshBackendData() {
        MembersEndpoint.getMembersUsingGET().then(function (members) {
            console.log("Retrieved members:", members.obj);
            $scope.$apply(function () {
                $scope.members = members.obj;
            });
            BooksEndpoint.getBooksUsingGET().then(function (books) {
                console.log("Retrieved books:", books.obj);
                $scope.$apply(function () {
                    $scope.books = books.obj;
                });
                BorrowingsEndpoint.getBorrowingsUsingGET().then(function (borrowings) {
                    console.log("Retrieved borrowings:", borrowings.obj);
                    $scope.$apply(function () {
                        $scope.borrowings = borrowings.obj;
                    });
                });
            });
        });
    }

    this.$routerOnActivate = function (next) {
        console.log("Borrowings controller initialized.");
        refreshBackendData();
    };

    $scope.saveBorrowing = function (email, password, borrowingType) {
        console.log("Saving borrowing ", $scope.selectedBorrowing);
        BorrowingsEndpoint.createBorrowingUsingPOST({borrowing: $scope.selectedBorrowing}).then(function () {
            console.log("Saved borrowing.");
            refreshBackendData();
            $('#edit-modal').modal('hide');
        });
    };

    $scope.editBorrowing = function (borrowing) {
        $scope.selectedBorrowing = borrowing;
        $('#edit-modal').modal('show'); // this is ugly, but I don't care.
    };

    $scope.selectBorrowingToDelete = function (borrowing) {
        $scope.borrowingToDelete = borrowing;
        $('#delete-modal').modal('show');
    };

    $scope.deleteBorrowing = function () {
        BorrowingsEndpoint.deleteBorrowingUsingDELETE({id: $scope.borrowingToDelete.id}).then(function() {
            console.log("Deleted borrowing with id", $scope.borrowingToDelete.id);
            $('#delete-modal').modal('hide');
            refreshBackendData();
        });
    };

    $scope.newBorrowing = function () {
        $scope.selectedBorrowing = {
            id: null,
            bookId: null,
            memberId: null,
            timeOfBorrowing: null,
            deadline: null
        };
        $('#edit-modal').modal('show');
    };
}
