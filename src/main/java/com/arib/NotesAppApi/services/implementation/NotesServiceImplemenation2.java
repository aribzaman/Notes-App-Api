//package com.arib.NotesAppApi.services.implementation;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.ExitTestApi.DTO.NotesDTO;
//import com.ExitTestApi.DTO.NotesDTOMapper;
//import com.ExitTestApi.DTO.ResponseMessage;
//import com.ExitTestApi.dao.NotesDao;
//import com.ExitTestApi.entities.Notes;
//import com.ExitTestApi.entities.User;
//import com.ExitTestApi.exception.ResourceNotFoundException;
//import com.ExitTestApi.services.NotesService;
//import com.ExitTestApi.services.UserService;
//
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//@Service
//public abstract class NotesServiceImplemenation2 implements NotesService {
//
//	private final NotesDTOMapper nDTOm;
//	private final NotesDao nd;
//	private final UserService us;
//
//	@Override
//	public Notes findById(int id) {
////		System.out.println("id in getById "  + id);
//		return nd.findById(id).orElseThrow(() -> new ResourceNotFoundException("No note present with this ID"));
//	}
//
//	@Override
//	public List<NotesDTO> getTenNotes1(int userID) {
//		us.findById(userID);
//		List<Notes> notes = nd.getTenNotes(userID); // nd.findTop10ByUser_idOrderByIdDesc(id);
//		if (notes.isEmpty()) {
//			throw new ResourceNotFoundException("No notes present for this ID");
//		}
//		return notes.stream().map(nDTOm).collect(Collectors.toList());
//	}
//
//	// --------------------------- SAVE FUNCTION with a Combined DTO
//
//	@Override
//	public ResponseEntity<ResponseMessage> save2(NotesDTO note) {
//		Notes newNote = nDTOm.applyReverse(note);
//		try {
//			nd.save(newNote);
//			return ResponseEntity.created(null)
//					.body(new ResponseMessage("Note Added successfully!", HttpStatus.CREATED));
//		} catch (Exception e) {
//			System.out.println(e + "\n" + e.getClass());
//			throw new RuntimeException("Something went wrong in Creaion");
//		}
//	}
//
//	@Override
//	public ResponseEntity<ResponseMessage> update2(NotesDTO note) {
////		System.out.println(findById(note.id()));
////		User nuser = us.findById(note.user());
////		Notes nnote = new Notes(note.id(), note.title(), note.content(), nuser, new Date()); //custom cont. for escaping the need to even bring created date
//		note.asUpdate();
//		Notes nnote = nDTOm.applyReverse(note, note.id());
//		System.out.println("sav note" + nnote);
//		try {
//			nd.save(nnote);
//			return ResponseEntity.ok().body(new ResponseMessage("Note Updated successfully!", HttpStatus.OK));
//		} catch (Exception e) {
//			System.out.println(e + "\n" + e.getClass());
//			throw new RuntimeException("Something went wrong in Updating");
//		}
//	}
//
////-------------------------------------
//


//	@Override
//	public ResponseMessage save(NotesDTO note) {
//		User nuser = us.findById(note.user());
//		Notes nnote = new Notes(note.title(), note.content(), nuser, new Date());
//		try {
//			nd.save(nnote);
//			return new ResponseMessage("Note Added successfully!", HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e + "\n" + e.getClass());
//			throw new RuntimeException("Something went wrong in Creaion");
//		}
//	}
//
//	@Override
//	public ResponseMessage update(NotesDTO note) {
//		us.findById(note.user());
////		Notes nnote = new Notes(note.id(), note.title(), note.content(), nuser, new Date()); //custom cont. for escaping the need to even bring created date
//		Notes nnote = nDTOm.applyReverse(note, note.id());
//		try {
//			nd.save(nnote);
//			return new ResponseMessage("Note Updated successfully!", HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e + "\n" + e.getClass());
//			throw new RuntimeException("Something went wrong in Updating");
//		}
//	}
//
//
////------------------------------ PAGINATION
//
////	@Override
////	public PageRequest getNotesByPages(int page, int size) {
////		// Create a Sort object to reverse the order by the "id" column
////		Sort sort = Sort.by(Direction.DESC, "id");
////		// Create a PageRequest with sorting
////		PageRequest pageable = PageRequest.of(page, size, sort);
////		// Fetch data using the reversed PageRequest
////		return pageable;
////	}
//
////	@Override
////	public Page<Notes> getNotesByPages(int page, int size) {
////		Sort sort = Sort.by(Direction.DESC, "id");
////		PageRequest pageable = PageRequest.of(page, size, sort);
////		return nd.findAll(pageable);
////	}
//
//}


