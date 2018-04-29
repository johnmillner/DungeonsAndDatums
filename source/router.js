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
  { path: '/',  component: Splash },
  { path: '/Campaign',  component: Campaign },
  { path: '/Charater',  component: Charater },
  { path: '/CreateCampaign',  component: CreateCampaign },
  { path: '/CreateCharacter',  component: CreateCharacter },
  { path: '/CreateItem',  component: CreateItem },
  { path: '/createSpell',  component: createSpell },
  { path: '/Main',  component: Main },
  { path: '/Settings', component: Settings }
];

export const Router = new VueRouter({
  routes
});
