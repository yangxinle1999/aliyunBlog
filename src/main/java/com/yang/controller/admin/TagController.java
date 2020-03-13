package com.yang.controller.admin;

import com.yang.po.Tag;
import com.yang.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")  //分页查询
    public String tags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable
            , Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes attributes){
        Tag type2=tagService.getTagByName(tag.getName());
        if(type2!=null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (bindingResult.hasErrors()){
            return "admin/tag-input";
        }
        Tag type1=tagService.savaTag(tag);
        if (type1==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag type, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes attributes){
        Tag type2=tagService.getTagByName(type.getName());
        if(type2!=null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (bindingResult.hasErrors()){
            return "admin/tag-input";
        }
        Tag type1=tagService.updateTag(id, type);
        if (type1==null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
