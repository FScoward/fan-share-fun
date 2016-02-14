System.register(['angular2/platform/browser', './menu.component', './events.component'], function(exports_1) {
    var browser_1, menu_component_1, events_component_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (menu_component_1_1) {
                menu_component_1 = menu_component_1_1;
            },
            function (events_component_1_1) {
                events_component_1 = events_component_1_1;
            }],
        execute: function() {
            // bootstrap(LoginComponent);
            browser_1.bootstrap(menu_component_1.MenuComponent);
            browser_1.bootstrap(events_component_1.EventsComponent);
        }
    }
});
