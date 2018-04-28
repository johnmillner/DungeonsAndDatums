import Router from "/source/router.js";
import App from "/source/components/app.vue";

//  Created by:   John Millner
//  Created for:  Dungeons and Datums

//launches Vue instance - inserts into blank object called app
const var v = new Vue({
  el: '#app',
  Router,
  template: '<App/>'
  components: { App }
})
