<!--
	Created by:		John Millner
	Created for:	Dungeons & Datums
-->

<template>
	<div id = "header" class="container-fluid">
		<div class = "row justify-content-around pt-3 pb-4">
			<div id="nameSake" class = "col-4 d-flex align-items-center justify-content-center">
				<img src="logo/nameLightSmall.png" alt="Dungeons and Datums" height = "75px" />
			</div>

			<div id = "login" class = "col-4 d-flex align-items-center  justify-content-center form-group">
			<input v-model="login.email" placeholder="email" class="form-control-sm col-xs-2" required/>
			<input v-model="login.password" placeholder="password" type="password" class="form-control-sm col-xs-2" required/>
			<button class = "btn btn-warning" v-on:click="submit">Login <i class="fas fa-sign-in-alt"></i></button>
			</div>

			<script type="text/javascript" src="script/login.js"></script>

		</div>
	</div>

	<div id = "content" class = "container-fluid " >
		<div class = "row d-flex flex-row justify-content-around">
			<div id = "image" class ="col-4">
				<img src="images/DnD_Goblin-clear.png" class="img-fluid">
			</div>

			<div id="info" class = " col-6 text-center" >
				<div id="stuff" class="panel-group">
					<div id="accordion">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
									Welcome to Dungeons and Datums
									</button>
								</h5>
							</div>

							<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
								<div class="card-body">
									<p>
										This is your one stop application for a better, more integrated Dungeons and Dragons experience. Created by two nerdy players that realized there could be a better way than the pencil and paper of yore. Dungeons and Datums is not a replacement for Dungeons and Dragons in any way, but a useful set of tools to help keep things flowing, use it when at the table, or over VoIP, or however you and your campaign play, Dungeons and Datums will keep you playing and enjoying!
										<br><br>
										Easily create, manage, and play your character, either standalone, or inside of a campaign group within Dungeons and Datums. Manage hit points, modifiers, spells, spell slots, items, and pretty much everything else through a simple to use and easy to manage interface that will keep you in the game while reducing the headaches of busting out a calculator or the players handbook every encounter.
										<br><br>
										Create, manage, and interact with your players with a well equipped set of tools for our Dungeon Masters. Easily join and drop players, manage XP and leveling, item and loot dispersal, Health and damage. Dungeons and Datums allows you to create rich encounters of complex monster groups and NPC’s that are easier to manage and keep track of than in paper. Keep actions logged for review on disputes, or for easier backtracking (it happens to all of us!)
										<br><br>
										TL;DR: If you like Dungeons and Dragons, sign up to Dungeons and Datums below or on our Android App!
									</p>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingTwo">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
										Player Features
									</button>
								</h5>
							</div>
							<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
								<div class="card-body">
									<p>
										With Dungeons and Datums easily manage and add rich flavor to all of your characters in an intuitive way that gives you access to every little detail of your character while keeping the important and most used stuff always within fingers reach.
										<br><br>
										Dungeons and Datums allows for a player character to replace their character sheet for an always up to date version stored in the mythical “Cloud” that can be access easily from any device.
										<br><br>
										But wait! There’s more!
										<br><br>

										Beyond merely text and fields, Dungeons and Datums can manage all those complex modifiers and whatnot, while also keeping track of spell slots, what spells have been readied and spent, health, and XP with leveling features built in. Manage and use your weapons, equipment, items, and money while always knowing exactly how much you have left and what to do with them.
										<br><br>

										Do you have a feat that you always forget to use because its buried in the Player’s Handbook, stay reminded, and know what it does so you can play your character exactly as you want to play them.
										<br><br>

										Dungeons and Datums takes care of the mechanics so you can focus on actually playing the game.
									</p>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingThree">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
										Dungeon Master Features
									</button>
								</h5>
							</div>
							<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
								<div class="card-body">
									<p>
										This is where it gets good.
										<br><br>
										Know at a glance immediately all the up-to-date health, stats, modifiers, and more from your players so that you know when you can turn it up a notch or brace for a TPK! Manage team wide effects or specify subgroups or even individual characters for health, damage, XP, money, loot and so on. Make custom items, or even new spells and attacks for those truly custom campaigns. Create rich NPC that are more than “another thing to manage.”
										<br><br>
										Potentially the most useful tool in Dungeons and Datums is the Encounter Manager. Here a DM can pit their group, or subgroup against any monster imaginable. Easily assemble a group of monsters for an encounter, either through the ones that are common place in the Monsters Manual and a few other widely published books, or create your own custom abominations and cute fluffy beasts. And all while your doing this, your team knows who they’re fighting and you know all your monster’s health and attacks at a glance just like your group.
										<br><br>
										Dungeons and Datums gives you the tools you need so you can create the best campaign for both yourself and your group.
									</p>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingFour">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
										Encoutners
									</button>
								</h5>
							</div>
							<div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
								<div class="card-body">
									<p>
										The pièce de résistance of Dungeons and Datums (so far! We’re just getting started!)
										<br><br>
										Encounters lets a Dungeon Master quickly and effectively create and manage a monster group pitted against the player group (or a subgroup thereof. )  Pick your players that are fighting, pick your monsters and play it out, but this time with the acute but accessible knowledge of all your players and all your monsters health, stats, AC, DC’s and Bonuses, and Modifiers without flipping through countless Character Sheets and Monster Manuals.
										<br><br>
										A// Pushes posts to the server when called.
				axios.post( `https://dungeonsanddatums.com/test/server/login.php`, vm.login )
				.then(function (response) { vm.message = "your name is: " + response.data.name; console.log(response); })
				.cs the DM: have the power to instantly know everything you need to know about your players and monsters stats, attacks, spells, and abilities on the fly.
										As the player: know everything about your character and what you’ve got to use to get through. Know what monsters your facing and what effects have been placed on them, without ruining immersion by knowing everything about the monsters.
										<br><br>
										Are you the kind of stats nerd that we are? Do you want a play-by-play replay of what just happened? Not to fear, every action can be logged and reviewed so you can know exactly how much damage you did with that Great Maul, or just how easily that Tarrasque TPK’d your poor, poor group.
									</p>
								</div>
							</div>
						</div>
						<div class="card">
							<div class="card-header" id="headingFive">
								<h5 class="mb-0">
									<button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
										MOAR!
									</button>
								</h5>
							</div>
							<div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion">
								<div class="card-body">
									<p>
										Machine Learning to perfectly create a challenging group of monsters for an encounter tailored to your specific campaign.
										<br><br>
										Random Loot Generator<Br>
										Random Shop Generator ( with flavor?! ) <br>
										Dice Roller (Might come sooner than expected!)<br>
										and a whole lot more once we release Alpha!<br>
										<br><br>
										If you like what we’re doing, support us by tossing a few bucks our way. Or, if you're with the Wizards of the Coast and like our style, come hire us :smiley:
									</p>
								</div>
							</div>
						</div>
					</div>

					<button class ="btn btn-warning btn-block" v-on:click="signup">Join Now!</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>

	export default
	{
		name = "splash",

		data()
		{

		}

		methods:
		{
			submit: function()
			{
				// Pushes posts to the server when called.
				axios.post( `https://dungeonsanddatums.com/test/server/login.php`, vm.login )
				.then(function (response) { vm.message = "your name is: " + response.data.name; console.log(response); })
				.catch( function (error) { vm.message="there was an error: " + error; })
			}
		}
	}

</script>
