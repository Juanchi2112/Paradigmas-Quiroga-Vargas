module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
where

import City ( City, nameC, distanceC )
import Quality ( Quality )
import Link ( Link, newL, linksL, capacityL )
import Tunel ( connectsT, delayT, newT, usesT, Tunel )

data Region = Reg [City] [Link] [Tunel] deriving (Show, Eq)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region 
foundR region@(Reg cities links tunels) city | city `elem` cities = region
                                             | nameInRegion city region = error "Ya hay una ciudad con el mismo nombre"
                                             | pointInRegion city region = error "Ya existe una ciudad con la misma ubicacion" 
                                             | otherwise = Reg (cities ++ [city]) links tunels


linkR :: Region -> City -> City -> Quality -> Region
linkR region@(Reg cities links tunels) c1 c2 qua | linkedR region c1 c2 = region
                                                 | elem c1 cities && elem c2 cities = Reg cities (links ++ [newL c1 c2 qua]) tunels 
                                                 | otherwise = error "Alguna de las ciudades no se encuentra en la región"


tunelR :: Region -> [ City ] -> Region 
tunelR region@(Reg cities links tuneles) cityList | length cityList <= 1 = error "La lista de ciudades debe contener al menos dos ciudades"
                                                  | connectedR region (head cityList) (last cityList) = region
                                                  | null (linksBetweenCities region cityList) = error "No hay capacidad disponible para un tunel más"
                                                  | checksLinks region cityList = Reg cities links (tuneles ++ [newT (linksBetweenCities region cityList)])
                                                  | otherwise = error "No todas las ciudades de la lista se encuentran enlazadas"

connectedR :: Region -> City -> City -> Bool
connectedR (Reg _ _ tunels) c1 c2 = foldr (\x acc -> connectsT c1 c2 x || acc) False tunels

linkedR :: Region -> City -> City -> Bool 
linkedR (Reg _ links _) c1 c2 = foldr (\x acc -> linksL c1 c2 x || acc) False links

delayR :: Region -> City -> City -> Float 
delayR region c1 c2 = delayT (convertTunel (getSharedTunel region c1 c2))

availableCapacityForR :: Region -> City -> City -> Int
availableCapacityForR region c1 c2 | linkedR region c1 c2 = capacityL link - usedCapacity region link   
                                   | otherwise = error "Las ciudades no estan enlazadas"
    where link = convertLink (getSharedLink region c1 c2)


checksLinks :: Region -> [City] -> Bool
checksLinks _ [] = True
checksLinks _ [_] = True
checksLinks region (city1:city2:rest) = linkedR region city1 city2 && checksLinks region (city2:rest)

linksBetweenCities :: Region -> [City] -> [Link]
linksBetweenCities _ [] = []
linksBetweenCities _ [_] = []
linksBetweenCities region (city1:city2:rest) = if availableCapacityForR region city1 city2 >= 1 then convertLink (getSharedLink region city1 city2): linksBetweenCities region (city2:rest)
                                                else []

getSharedLink :: Region -> City -> City -> Maybe Link
getSharedLink (Reg _ links _) c1 c2 = foldr (\x acc -> if linksL c1 c2 x then Just x else acc) Nothing links

getSharedTunel :: Region -> City -> City -> Maybe Tunel
getSharedTunel (Reg _ _ tunels) c1 c2 = foldr (\x acc -> if connectsT c1 c2 x then Just x else acc) Nothing tunels

convertLink :: Maybe Link -> Link
convertLink (Just link) = link
conevertLink Nothing = error "No existe el link entre las ciudades"

convertTunel :: Maybe Tunel -> Tunel
convertTunel (Just tunel) = tunel
convertTunel Nothing = error "No existe el tunel entre las ciudades"

usedCapacity :: Region -> Link -> Int
usedCapacity region@(Reg _ _ tunelList) link = sum [1 | tunel <- tunelList, usesT link tunel]

nameInRegion :: City -> Region -> Bool
nameInRegion city (Reg cityList _ _) = nameC city `elem` [nameC n| n <- cityList]

pointInRegion :: City -> Region -> Bool
pointInRegion city (Reg cityList _ _)  = 0 `elem` [distanceC city n| n <- cityList] 