//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import type="module" Router from "/source/router.js";
import App from "/source/components/app.vue";

//launches Vue instance - inserts into blank object called app
const var v = new Vue({
  el: '#app',
  Router,
  template: '<App/>'
  components: { App }
})
