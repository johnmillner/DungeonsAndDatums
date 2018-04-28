//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import Router from "/source/router.js"
import App from "/source/components/app.vue"

//launches Vue instance - inserts into blank object called app
new Vue({
  el: '#app',
  Router,
  template: '<App/>'
  components: { App }
})
