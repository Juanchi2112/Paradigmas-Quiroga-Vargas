module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = Tun linkList

city_extreme :: City -> Tunel -> Bool -- indica si una ciudad es un extremo del tunel
city_extreme city (Tun linkList) = ((connectsL city firstLink) && not (connectsL city secondLink)) || ((connectsL city lastLink) && not (connectsL city secondLastLink))
    where
    firstLink = linkList !! 0
    secondLink = linkList !! 1
    len = length linkList
    secondLastLink = linkList !! (len - 2)
    lastLink= linkList !! (len - 1)

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = city1 /= city2 && city_extreme city1 tunel && city_extreme city2 tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linklist) = elem link linklist

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun linkList) = foldr (+) 0 [delayL link | link <- linkList]