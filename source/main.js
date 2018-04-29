//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import { Router } from "./router.js";

// launches Vue instance - inserts into blank object called app
export var vm = new Vue({
  el: '#app',
  Router,
  template: '<App/>',
  components: { 'app' : httpVueLoader('./components/app.vue') }
})
