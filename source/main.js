//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import Router from "source/router";
import App from "/source/components/app";

//launches Vue instance - inserts into blank object called app
const var v = new Vue({
  el: '#app',
  Router,
  template: '<App/>'
  components: { App }
})
