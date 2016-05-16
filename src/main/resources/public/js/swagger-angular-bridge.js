var swaggerWithCallback = new SwaggerClient({
    url: "/v2/api-docs",
    usePromise: true,
    success: function () {

    }
}).then(function (swag) {
    var backendModule = angular.module('backend', []);
    backendModule.service('MembersEndpoint', function () { return swag["member-controller"]});
    backendModule.service('BooksEndpoint', function () { return swag["book-controller"]});
    backendModule.service('BorrowingsEndpoint', function () { return swag["borrowing-controller"]});
    angular.bootstrap(document, ['app']);
}).catch(function (error) {
    console.error('Swagger promise rejected', error);
});