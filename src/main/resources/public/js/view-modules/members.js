angular.module('members', ['backend'])
    .component('membersComponent', {
        templateUrl: '/templates/members.html',
        controller: ['$scope', 'MembersEndpoint', MembersController]
    });

function MembersController($scope, MembersEndpoint) {

    // TODO get this stuff from the backend.
    $scope.memberTypes = ["MEMBER", "ADMIN"];

    $scope.selectedMember = {
        id: null,
        email: null,
        password: null,
        memberType: null
    };

    $scope.memberToDelete = null;

    function refreshMemberList() {
        MembersEndpoint.getMembersUsingGET().then(function (members) {
            console.log("Retrieved members:", members.obj);
            $scope.$evalAsync(function () {
                $scope.members = members.obj;
            });
        });
    }

    this.$routerOnActivate = function (next) {
        console.log("Members controller initialized.");
        refreshMemberList();
    };

    $scope.saveMember = function () {
        console.log("Saving member ", $scope.selectedMember);
        MembersEndpoint.createMemberUsingPOST({member: $scope.selectedMember}).then(function () {
            console.log("Saved member.");
            refreshMemberList();
            $('#edit-modal').modal('hide');
        });
    };

    $scope.editMember = function (member) {
        $scope.selectedMember = member;
        $('#edit-modal').modal('show'); // this is ugly, but I don't care.
    };

    $scope.selectMemberToDelete = function (member) {
        $scope.memberToDelete = member;
        $('#delete-modal').modal('show');
    };

    $scope.deleteMember = function () {
        MembersEndpoint.deleteMemberUsingDELETE({id: $scope.memberToDelete.id}).then(function() {
            console.log("Deleted member with id", $scope.memberToDelete.id);
            refreshMemberList();
            $('#delete-modal').modal('hide');
        });
    };

    $scope.newMember = function () {
        $scope.selectedMember = {
            id: null,
            email: null,
            password: null,
            memberType: null
        };
        $('#edit-modal').modal('show');
    };

}
