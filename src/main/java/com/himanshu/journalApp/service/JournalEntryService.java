package com.himanshu.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.himanshu.journalApp.entity.JournalEntry;
import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;
	
	public void saveEntry(JournalEntry journalEntry, String userName) {
		try {
			User user = userService.findByUserName(userName);
			journalEntry.setDate(LocalDateTime.now());
			JournalEntry saved = journalEntryRepository.save(journalEntry);
			user.getJournalEntries().add(saved);
			userService.saveEntry(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveEntry(JournalEntry journalEntry) {
		try {
			journalEntryRepository.save(journalEntry);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<JournalEntry> getAll(){
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteById(ObjectId id, String userName) {
		User user = userService.findByUserName(userName);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		userService.saveEntry(user);
		journalEntryRepository.deleteById(id);
	}
	
}
