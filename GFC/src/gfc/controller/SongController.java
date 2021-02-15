package gfc.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gfc.dto.Song;
import gfc.service.SongService;

@Controller
@RequestMapping(path = {"/song"})
public class SongController {
	@Autowired
	private SongService songService;
	
	@GetMapping("/addSongForm")
	public String addsongForm() {
		return "song/addSongForm";
	}

	@PostMapping("/addSong")
	public String AddSong(Song song) throws ParseException {
//		System.out.println(song);
		String temp = songService.translate(song.getKlyric());
		song.setFlyric(songService.convertToData(temp));
		System.out.println(song);
		
		int result = songService.addSong(song);
		if (result == 1)
			return "redirect:/song/songList";
		else
			return "redirect:/song/addSongForm";
	}
	
	@GetMapping("/songList")
	public String list(Model model) {
		List<Song> songs = songService.getSongList();
		model.addAttribute("songList", songs);
		return "song/songList";
	}
	
	@GetMapping("/songDetail")
	public String songDetail(Model model,int scode) {
//		System.out.println(scode);
		Song song = songService.getSong(scode);
		model.addAttribute("song",song);
//		System.out.println(song);
		return "song/songDetail";
	}
}
