'''
def tunel(link_list: list):
    for link in link_list:
        for link2 in link_list[1:]:
            match1 = link2.index(link[0])
            match2 = link2.index(link[1])
'''
def verificar_conexion_valida(lista_tuplas):
    ciudades = set()
    conexiones = set()

    # Obtener la lista de todas las ciudades y conexiones en las tuplas
    for tupla in lista_tuplas:
        ciudad1, ciudad2 = tupla
        ciudades.add(ciudad1)
        ciudades.add(ciudad2)
        conexiones.add((ciudad1, ciudad2))
        conexiones.add((ciudad2, ciudad1))

    # Encontrar ciudades con un solo enlace
    extremos = []
    for ciudad in ciudades:
        enlaces = sum(1 for c1, c2 in conexiones if c1 == ciudad)
        if enlaces == 1:
            extremos.append(ciudad)

    if len(extremos) != 2:
        return False, None, None

    punto_entrada, punto_salida = extremos

    return True, punto_entrada, punto_salida

# Ejemplo de uso
tuplas_ciudades = [("A", "B"),("D", "E"), ("B", "C"), ("C", "D"), ("A", "B")]
conexion_valida, extremo_entrada, extremo_salida = verificar_conexion_valida(tuplas_ciudades)

if conexion_valida:
    print(f"La conexión entre {extremo_entrada} y {extremo_salida} es válida.")
else:
    print("La conexión no es válida.")

            
                
    
