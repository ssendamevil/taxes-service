package com.enu.taxes_service.domain;

import lombok.Getter;

@Getter
public enum PropertyType {
    RESIDENTIAL,       // Жилая недвижимость (квартиры, дома)
    COMMERCIAL,        // Коммерческая недвижимость (офисы, магазины)
    LAND,              // Земельные участки
    VEHICLE,           // Транспортные средства (автомобили, мотоциклы)
    INDUSTRIAL,        // Промышленные объекты (заводы, склады)
    AGRICULTURAL,      // Сельскохозяйственные земли и постройки
    INVESTMENT,        // Инвестиционная недвижимость (апартаменты, отели)
    OTHER              // Другое имущество
}
