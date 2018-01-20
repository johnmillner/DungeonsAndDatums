import random
import operator

class Monster:
	def __init__ ( self, name, hp, ac, attacks, items ):
		self.name 		= name
		self.hp 		= hp
		self.ac 		= ac
		self.attacks 	= attacks
		self.maxAttack 	= 0
		self.initiative = 0
		self.team		= None
		
		for attack in attacks:
			if attack.avg > self.maxAttack:
				self.maxAttack = attack.avg
		
class Attack:
	def __init__ ( self, name, noDice, sizeDice, modifier, avg )
		self.name 		= name
		self.noDice 	= noDice
		self.sizeDice 	= sizeDice
		self.modifier 	= modifier
		self.avg 		= avg
						
def simlulate_1v1( monsters ) :
	counter = 0
	# roll for initiative - 50%
	if random.random() > .5:
		counter = 1
		
	while monster1.hp > 0 and monster2.hp > 0:
		hit = random.randint(1, 20)
		if hit > monsters[ counter % 2 + 1 ].ac:
			monsters[ counter % 2 + 1 ].hp = monsters[ counter % 2 + 1 ].hp - monsters[ counter % 2 ].maxAttack
			
		counter = counter + 1	
		
	if  monster1.hp > 0 :
		victor = monster1
	else:
		victor = monster2
	print "Victor is ", victor.name, " with a health of ", victor.hp," and completed in ", counter, " turns!"
	

def simulate_manyMonsters ( team1, team2 ):
	counter = 0
	
	#assign team numbers
	for monster in team1:
		monster.team = 1	
	for monster in team2:
		monster.team = 2
		
	#assign initiative for each member
	for monster in team1 + team2:
		monster.initiative = random.random()
	
	#create fighting order
	order = sorted( team1+team2, key = operator.attrgetter('initiative') )
	
	while len( team1 ) > 0 and len( team2 ) > 0:
		#this guy attacks a random guy on the other team
		attacker = order[ counter % ( len( team) + len( team2 )) ]
		
		#determine which team the attacker and defender are on
		if attacker.team is 1:
	  		defense = team2
		else:
			defense = team1
			
		#pick random guy on defense to take the hit
		catcher = random.randin( 0, len( defense - 1) )
		
		#determine hit chance
		hit = random.randint( 1, 20 )			
		
		#see if it actually hit
		if hit > defense[ catcher ].ac:
			defense[ catcher ].hp = defense[ catcher].hp - attacker.maxAttack
			
		#determine if catcher died
		if defense[ catcher ].hp <= 0:
			defense.remove( catcher )
			
		counter = counter + 1 
		
	if ( lean( team1 ) > 0 ):
		victor = "team 1"
	else:
		victor = "team 2"
	print "Victor is ", victor.name," and completed in ", counter, " turns!"
	
	
def main:
	
	
if __name__ == "__main__": 
	main()
	
