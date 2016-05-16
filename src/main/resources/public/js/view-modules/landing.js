angular.module('landing', [])
    .component('landingComponent', {
        templateUrl: '/templates/landing.html',
        controller: LandingController
    });

function LandingController() {
    
    this.$routerOnActivate = function(next) {
        console.log("Landing controller initialized.");
    };
    
}
