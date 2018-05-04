package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


@Autowired
RoomRepository roomRepository;

    @RequestMapping("/")
    public String showMainPage()
    {
        return "list";
    }

    @RequestMapping("/addlist")
    public String listRooms(Model model)
    {
        model.addAttribute("rooms", roomRepository.findAll());
        return "list";
    }

    @RequestMapping("/add")
    public String roomForm(Model model)
    {
        model.addAttribute("aRoom", new Room());
        return "roomForm";
    }

    @RequestMapping("/saveroom")
    public String saveRoom(@ModelAttribute("aRoom") Room toSave, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "roomForm";
        }
        roomRepository.save(toSave);
        return "redirect:/homepage";
    }

    @RequestMapping("/changesstatus/{id}")
    public String rentedReturn(@PathVariable("id")long id)
    {
        Room thisRoom = roomRepository.findRoomById(id);
        thisRoom.setRented(!thisRoom.isRented());
        roomRepository.save(thisRoom);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showRoom (@PathVariable("id")long id, Model model)
    {
        model.addAttribute("aRoom", roomRepository.findRoomById(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateRoom (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("aRoom", roomRepository.findRoomById(id));
        return "roomForm";
    }


    @RequestMapping("/delete/{id}")
    public String delRoom(@PathVariable("id") long id, Model model)
    {
        roomRepository.deleteById(id);
        return "redirect:/";
    }

}
