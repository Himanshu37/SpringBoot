package com.himanshu.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.service.JournalEntryService;
import com.himanshu.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;

	@GetMapping("{userName}")
	public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
		User user = userService.findByUserName(userName);
		List<JournalEntry> all = user.getJournalEntries();
		if(null != all && !all.isEmpty()) {
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("{userName}")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
		try {
			journalEntryService.saveEntry(myEntry, userName);
			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		 Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		 if(journalEntry.isPresent()){
			 return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("id/{myId}/{userName}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
		journalEntryService.deleteById(myId, userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("id/{myId}/{userName}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName,@RequestBody JournalEntry newEntry) {
		JournalEntry old = journalEntryService.findById(myId).orElse(null);
		
		if(null != old) {
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
			journalEntryService.saveEntry(old);
			return new ResponseEntity<>(old,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
