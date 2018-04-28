//  Created by:   John Millner
//  Created for:  Dungeons and Datums

import Header           from '/source/components/header.vue';
import SplashHeader     from '/source/components/splashHeader.vue';
import Menu             from '/source/components/menu.vue';

import Campaign         from 'source/pages/campaign.vue';
import Charater         from 'source/pages/character.vue';
import CreateCampaign   from 'source/pages/createCampaign.vue';
import CreateCharacter  from 'source/pages/createCharacter.vue';
import CreateItem       from 'source/pages/createItem.vue';
import createSpell      from 'source/pages/createSpell.vue';
import Main             from 'source/pages/main.vue';
import Settings         from 'source/pages/settings.vue';
import Splash           from 'source/pages/splash.vue';

Vue.use( Router );

const routes =
[
  { path: '/'  component: Splash },
  { path: '/Campaign'  component: Campaign },
  { path: '/Charater'  component: Charater },
  { path: '/CreateCampaign'  component: CreateCampaign },
  { path: '/CreateCharacter'  component: CreateCharacter },
  { path: '/CreateItem'  component: CreateItem },
  { path: '/createSpell'  component: createSpell },
  { path: '/Main'  component: Main },
  { path: '/Settings'  component: Settings }
];

export default const Router = new VueRouter({
  routes
});
