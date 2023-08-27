module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList | (length linkList) >= 1 = Tun linkList
              | otherwise = error "La lista está vacía"


tunel_extreme :: City -> Tunel -> Bool
tunel_extreme city (Tun linkList) = (connectsL city firstLink && not (connectsL city secondLink)) || (connectsL city lastLink && not (connectsL city secondLastLink))
    where
        firstLink = head linkList
        secondLink = linkList !! 1
        len = length linkList
        secondLastLink = linkList !! (len - 2)
        lastLink = last linkList


connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel@(Tun linkList) | (length linkList >= 2) = city1 /= city2 && tunel_extreme city1 tunel && tunel_extreme city2 tunel
                                           | (length linkList == 1) = city1 /= city2 && linksL city1 city2 (linkList !! 0)

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linklist) = elem link linklist

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun linkList) = foldr (+) 0 [delayL link | link <- linkList]