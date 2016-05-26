angular.module('borrowings', [])
    .component('borrowingsComponent', {
        templateUrl: '/templates/borrowings.html',
        controller: ['$scope', 'BorrowingsEndpoint', 'BooksEndpoint', 'MembersEndpoint', 'Authority', BorrowingsController]
    });

function BorrowingsController($scope, BorrowingsEndpoint, BooksEndpoint, MembersEndpoint, Authority) {

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
    $scope.memberType = Authority.memberType;

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
        if (Authority.memberType == 'ADMIN') {
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
                            $scope.borrowings.forEach(function (borrowing, i) {
                                borrowing.expired = borrowing.deadline < new Date().getTime();
                                borrowing.deadlineText = moment(borrowing.deadline).format('YYYY-MM-D HH:mm:ss');
                                borrowing.timeOfBorrowingText = moment(borrowing.timeOfBorrowing).format('YYYY-MM-D HH:mm:ss');
                            })
                        });
                    });
                });
            });
        } else {
            BooksEndpoint.getBooksUsingGET().then(function (books) {
                console.log("Retrieved books:", books.obj);
                $scope.$apply(function () {
                    $scope.books = books.obj;
                });
                BorrowingsEndpoint.getMyBorrowingsUsingGET().then(function (borrowings) {
                    console.log("Retrieved borrowings:", borrowings.obj);
                    $scope.$apply(function () {
                        $scope.borrowings = borrowings.obj;
                        $scope.borrowings.forEach(function (borrowing, i) {
                            borrowing.memberEmail = Authority.email;
                            borrowing.expired = borrowing.deadline < new Date().getTime();
                            borrowing.deadlineText = moment(borrowing.deadline).format('YYYY-MM-D hh:mm:ss');
                            borrowing.timeOfBorrowingText = moment(borrowing.timeOfBorrowing).format('YYYY-MM-D hh:mm:ss');
                        })
                    });
                });
            });

        }
    }

    this.$routerOnActivate = function (next) {
        console.log("Borrowings controller initialized.");
        refreshBackendData();
    };

    $scope.saveBorrowing = function () {
        console.log("Saving borrowing ", $scope.selectedBorrowing);
        $scope.selectedBorrowing.deadline = moment($('#expiration-date').val()).unix() * 1000;
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
