import xml.etree.ElementTree as ET
from mysql.connector import MySQLConnection, Error

def connect():
	""" Conect to MySQL database """
	
	try:
		conn = mysql.connector.connect(host = 'localhsot', 
									   database = 'dungeonsAndData', 
									   user = 'root',
									   password = 'Littlefoot')
	if conn.is_connected():
		print('Connected to dungeonsAndData')
	except error as e:
		print(e)
	finally:
		conn.close()

def insert_monster(name, hp, ac):
	
	query = "INSERT INTO monsters(name, hp, ac) " \
            "VALUES(%s,%d, %d)"
	args = (name, ac, hp)
	
	try:
		conn = mysql.connector.connect(host = 'localhsot', 
									   database = 'dungeonsAndData', 
									   user = 'root'
									   password = 'Littlefoot')
	if conn.is_connected():
		print('Connected to dungeonsAndData')
	except error as e:
		print(e)
	finally:
		conn.close()
		
		
def insert_monster_attacks(name, attack):
	query = "INSERT INTO monseter_attacks(title,isbn) " \
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
		
def main():
	connect()
	#insert_monster("Aboleth", 135, 17)
	'''
	tree = ET.parse('../DnDAppFiles/Bestiary/Monster_Manual_Bestiary.xml')
	root = tree.getroot()

	for monster in root.findall('monster'):
		name =  monster.find('name').text
		ac =  monster.find('ac').text
		hp =  monster.find('hp').text
		insert_monster(name, hp, ac)
		
		for action in monster.findall('action'):
			if action.find('attack') is not None:
				attack = action.find('attack').text
	'''

	if __name == '__main__':
		main()
	

			