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
  {
    path: '/',
    component: { 'Splash' : httpVueLoader('./pages/splash.vue') }
  },
  {
    path: '/campaign',
    component: { 'Campaign' : httpVueLoader('./pages/campaign.vue') }
  },
  {
    path: '/charater',
    component: { 'Charater' : httpVueLoader('./pages/character.vue') }
  },
  {
    path: '/createCampaign',
    component: { 'CreateCampaign' : httpVueLoader('./pages/createCampaign.vue') }
  },
  {
    path: '/createCharacter',
    component: { 'CreateCharacter' : httpVueLoader('./pages/createCharacter.vue') }
  },
  {
    path: '/createItem',
    component: { 'CreateItem' : httpVueLoader('./pages/createItem.vue') }
  },
  {
    path: '/createSpell',
    component: { 'createSpell' : httpVueLoader('./pages/createSpell.vue') }
  },
  {
    path: '/main',
    component: { 'Main' : httpVueLoader('./pages/main.vue') }
  },
  {
    path: '/settings', 
    component: { 'Settings' : httpVueLoader('./pages/settings.vue') }
  }
];

export const Router = new VueRouter({
  routes
});
