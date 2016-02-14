System.register(['angular2/core', './service/event.service', 'angular2/http'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, event_service_1, http_1;
    var EventsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (event_service_1_1) {
                event_service_1 = event_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            EventsComponent = (function () {
                function EventsComponent(eventService) {
                    var _this = this;
                    this.eventService = eventService;
                    this.events = [];
                    console.log('Event Component constructor');
                    this.eventService.getEvents()
                        .subscribe(function (events) { return _this.events = events; });
                    //this.events.push({title: "nogizaka"});
                    //this.events.push({title: "AAAAAAA"});
                }
                EventsComponent = __decorate([
                    core_1.Component({
                        selector: 'events-comp',
                        templateUrl: '/assets/templates/events.html',
                        providers: [event_service_1.EventService, http_1.HTTP_PROVIDERS]
                    }), 
                    __metadata('design:paramtypes', [event_service_1.EventService])
                ], EventsComponent);
                return EventsComponent;
            })();
            exports_1("EventsComponent", EventsComponent);
        }
    }
});
