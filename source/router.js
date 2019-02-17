//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import Header           from './components/header.vue';
import SplashHeader     from './components/splashHeader.vue';
import Menu             from './components/menu.vue';

import Campaign         from './pages/campaign.vue';
import Charater         from './pages/character.vue';
import CreateCampaign   from './pages/createCampaign.vue';
import CreateCharacter  from './pages/createCharacter.vue';
import CreateItem       from './pages/createItem.vue';
import createSpell      from './pages/createSpell.vue';
import Main             from './pages/main.vue';
import Settings         from './pages/settings.vue';
import Splash           from './pages/splash.vue';

Vue.use( Router );

const routes =
[
  { path: '/',          name: 'Splash', component: Splash },
  { path: '/Campaign',  name: 'Campaign', component: Campaign },
  { path: '/Charater',  name: 'Charater', component: Charater },
  { path: '/CreateCampaign',    name: 'CreateCampaign', component: CreateCampaign },
  { path: '/CreateCharacter',   name: 'CreateCharacter', component: CreateCharacter },
  { path: '/CreateItem',    name: 'CreateItem', component: CreateItem },
  { path: '/createSpell',   name: 'createSpell', component: createSpell },
  { path: '/Main',      name: 'Main', component: Main },
  { path: '/Settings',  name: 'Settings', component: Settings }
];

export const Router = new VueRouter({
  routes
});
