module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = if  verificarConexion (tupleList linkList) == [Nothing, Nothing] then Tun [] else Tun linkList

tupleList linkList = [tupleLink x|x <- linkList]

verificarConexion :: [(String, String)] -> [Maybe String]
verificarConexion listaTuplas = do
    let ciudades = foldr (\(ciudad1, ciudad2) acc -> ciudad1 : ciudad2 : acc) [] listaTuplas
        conexiones = foldr (\(ciudad1, ciudad2) acc -> (ciudad1, ciudad2) : (ciudad2, ciudad1) : acc) [] listaTuplas
        extremos = [ciudad | ciudad <- ciudades, enlaces ciudad == 1]
        enlaces ciudad = length $ filter (\(c1, c2) -> c1 == ciudad) conexiones
    
    case extremos of
        [puntoEntrada, puntoSalida] -> [Just puntoEntrada, Just puntoSalida]
        _ -> [Nothing, Nothing]

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun linkList) = verificarConexion (tupleList linkList) == [Just (nameC city1), Just (nameC city2)] || verificarConexion (tupleList linkList) == [Just (nameC city2), Just (nameC city1)]


usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun linkList) = elem link linkList


delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (l:linkList)) =  delayL l + delayT (Tun linkList)