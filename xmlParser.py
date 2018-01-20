import xml.etree.ElementTree as ET
import mysql.connector 
import MySQLdb
import re

def insert_monster(name, hp, ac):
	
	#print name, ac, hp
	query = "INSERT INTO monsters(name, hp, ac) VALUES('%s',%d,%d);" % (MySQLdb.escape_string(name), ac, hp)
	
	conn = mysql.connector.connect(host = 'localhost', database = 'dungeonsAndData', user = 'root', password = 'Littlefoot')
	
	cursor = conn.cursor()
	cursor.execute("use dungeonsAndData;")
	cursor.execute(query)
	conn.commit()
	
	print "added ", name, " to database"
	conn.close()
		
		
def insert_monster_attacks(name, attack):
	query = "INSERT INTO monster_attacks(,) " \
            "VALUES(%s,%d, %d)"
	args = (name, ac, hp)
	
	try:
		db_config = read_db_config()
		conn = MySQLConnection(**db_config)
		
		cursor = conn.cursor()
		cursor.execute(query, args)
		
		if cursor.lastrowid:
			print('last insert id', cursor.lastrowid)
		else:
			print('last insert id not found')
		
		conn.commit()
	except Error as error:
		print(error)
	
	finally:
		cursor.close()
		conn.close()
		
		
def insert_attacks(name, numOfDice, sizeOfDice, modifier, avgDmg):
	#print name, ac, hp
	query = "INSERT INTO attacks(name, numOfDice, sizeOfDice, modifier, avgDmg) VALUES('%s',%d,%d, %d, %d);" % (MySQLdb.escape_string(name), numOfDice, sizeOfDice, modifier, avgDmg)
	
	conn = mysql.connector.connect(host = 'localhost', database = 'dungeonsAndData', user = 'root', password = 'Littlefoot')
	
	cursor = conn.cursor()
	cursor.execute("use dungeonsAndData;")
	cursor.execute(query)
	conn.commit()
	
	print "added ", name, " to database"
	conn.close()
def main():
	#insert_monster('Aboleth', 135, 17)
	
	tree = ET.parse('../DnDAppFiles/Bestiary/Monster Manual Bestiary.xml')
	root = tree.getroot()

	for monster in root.findall('monster'):
		name =  monster.find('name').text
		ac =  monster.find('ac').text
		hp =  monster.find('hp').text
		hp = hp.split(' ')[0]
		ac = ac.split(' ')[0]
		
		
		hp = int(hp)
		ac = int(ac)
		
		
		
		#insert_monster(name, hp, ac)
		
		for action in monster.findall('action'):
			if action.find('attack') is not None:
				attack = action.find('attack').text
				
				attack = attack.replace(" ", "")
				print attack
				attack = re.split(r'[|d+]',attack)
				atkName = attack[0]
				atkAvgDmg = int(attack[1])
				atkNumOfDice = int(attack[2])
				atkSizeOfDice = int(attack[3])
				atkModifier = int(attack[4])
				insert_attacks(atkName, atkNumOfDice, atkSizeOfDice, atkModifier, atkAvgDmg)
				
				
	

if __name__ == '__main__':
	main()
	

			 	