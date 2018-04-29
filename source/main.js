//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import Router from "./router.js";
import App from "./components/app.vue";

// launches Vue instance - inserts into blank object called app
export var vm = new Vue({
  el: '#app',
  Router,
  template: '<App/>',
  components: { App }
})
