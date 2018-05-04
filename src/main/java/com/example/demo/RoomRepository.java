package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room , Long>{
    Room findRoomById(long id);
    //Iterable <Room> findAllByPrivateListing(boolean isPrivate);
}
