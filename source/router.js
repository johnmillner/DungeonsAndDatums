//  Created by:   John Millner
//  Created for:  Dungeons and Datums

Vue.use( router );

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

export const router = new VueRouter({
  routes
});
