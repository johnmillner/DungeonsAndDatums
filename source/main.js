//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import { router } from "./router.js";

// launches Vue instance - inserts into blank object called app
export var vm = new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { 'app' : httpVueLoader('./components/app.vue') }
})
