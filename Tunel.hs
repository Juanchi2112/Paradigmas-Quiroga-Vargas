module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = Tun linkList

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun linkList) = condition1 || condition2
  where
    notShared = notSharedCity
    firstElem = linkList !! 0
    secondElem = linkList !! 1
    len = length linkList
    secondLastElem = linkList !! (len - 2)
    lastElem = linkList !! (len - 1)
    condition1 = notShared firstElem secondElem == city1 || notShared firstElem secondElem == city2
    condition2 = notShared secondLastElem lastElem == city1 || notShared secondLastElem lastElem == city2

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linklist) = elem link linklist

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delay (Tun []) = 0
delayT (Tun (l:linkList)) = delayL l + delayT (Tun linkList) 
