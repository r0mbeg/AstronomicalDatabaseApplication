INSERT INTO `astronomicaldatabase`.`galaxy` (`NAME`, `TYPE`) VALUES ('Anromeda', 'Spiral');
INSERT INTO `astronomicaldatabase`.`galaxy` (`NAME`, `TYPE`) VALUES ('Large Magellanic Cloud', 'Irregular');
INSERT INTO `astronomicaldatabase`.`galaxy` (`NAME`, `TYPE`) VALUES ('Milky Way', 'Spiral');
INSERT INTO `astronomicaldatabase`.`galaxy` (`NAME`, `TYPE`) VALUES ('Whirlpoll', 'Spiral');


INSERT INTO `astronomicaldatabase`.`constellation` (`NAME`) VALUES ('Orion');
INSERT INTO `astronomicaldatabase`.`constellation` (`NAME`) VALUES ('Centauri');


INSERT INTO `astronomicaldatabase`.`star_system` (`NAME`, `GALAXY_NAME`, `CONSTELLATION_NAME`) VALUES ('Betelgeuse', 'Milky Way', 'Orion');
INSERT INTO `astronomicaldatabase`.`star_system` (`NAME`, `GALAXY_NAME`, `CONSTELLATION_NAME`) VALUES ('Centauri', 'Milky Way', 'Centauri');
INSERT INTO `astronomicaldatabase`.`star_system` (`NAME`, `GALAXY_NAME`) VALUES ('Solar', 'Milky Way');


INSERT INTO `astronomicaldatabase`.`star` (`NAME`, `SPECTRAL_CLASS`, `STAR_SYSTEM_NAME`) VALUES ('Sun', 'G2V', 'Solar');
INSERT INTO `astronomicaldatabase`.`star` (`NAME`, `STAR_SYSTEM_NAME`) VALUES ('Betelgeuse', 'Betelgeuse');
INSERT INTO `astronomicaldatabase`.`star` (`NAME`, `STAR_SYSTEM_NAME`) VALUES ('Alpha Centauri', 'Centauri');
INSERT INTO `astronomicaldatabase`.`star` (`NAME`, `STAR_SYSTEM_NAME`) VALUES ('Proxima Centauri', 'Centauri');


INSERT INTO `astronomicaldatabase`.`planet` (`NAME`, `TYPE`, `MASS`, `RADIUS`, `SEMI_MAJOR_AXIS`, `ORBITAL_ECCENTRICITY`, `STAR_SYSTEM_NAME`) VALUES ('Earth', 'earth type', '6*10^24 kg', '6400 km', '15*10^7 km', '0.017', 'Solar');
INSERT INTO `astronomicaldatabase`.`planet` (`NAME`, `TYPE`, `MASS`, `RADIUS`, `SEMI_MAJOR_AXIS`, `ORBITAL_ECCENTRICITY`, `STAR_SYSTEM_NAME`) VALUES ('Jupiter', 'gas giant', '2*10^27 kg', '70000 km', '8*10^8 km', '0.049', 'Solar');
INSERT INTO `astronomicaldatabase`.`planet` (`NAME`, `TYPE`, `MASS`, `RADIUS`, `SEMI_MAJOR_AXIS`, `ORBITAL_ECCENTRICITY`, `STAR_SYSTEM_NAME`) VALUES ('Mars', 'earth type', '6.4*10^23 kg', '3400 km', '2.3*10^8 km', '0.093', 'Solar');
INSERT INTO `astronomicaldatabase`.`planet` (`NAME`, `TYPE`, `MASS`, `RADIUS`, `SEMI_MAJOR_AXIS`, `ORBITAL_ECCENTRICITY`, `STAR_SYSTEM_NAME`) VALUES ('Venus', 'earth type', '5*10^24 kg', '6000 km', '11*10^7 km', '0.068', 'Solar');


INSERT INTO `astronomicaldatabase`.`satellite` (`NAME`, `PLANET_NAME`) VALUES ('Moon', 'Earth');
INSERT INTO `astronomicaldatabase`.`satellite` (`NAME`, `PLANET_NAME`) VALUES ('Phobos', 'Mars');
INSERT INTO `astronomicaldatabase`.`satellite` (`NAME`, `PLANET_NAME`) VALUES ('Deimos', 'Mars');

