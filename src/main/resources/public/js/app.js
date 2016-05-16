angular.module('app', ['ngComponentRouter', 'landing', 'members', 'borrowings', 'books'])

    .config(function ($locationProvider) {
        // $locationProvider.html5Mode(true);
    })

    .value('$routerRootComponent', 'app')

    .component('app', {
        template: '<navbar></navbar>' +
        '<ng-outlet></ng-outlet>',
        $routeConfig: [
            {path: '/landing', name: 'Landing', component: 'landingComponent', useAsDefault: true},
            {path: '/members', name: 'Members', component: 'membersComponent' },
            {path: '/books', name: 'Books', component: 'booksComponent' },
            {path: '/borrowings', name: 'Borrowings', component: 'borrowingsComponent' }
        ]
    })
    .directive('navbar', function () {
        return {
            templateUrl: '/templates/navbar.html'
        }
    })

;

