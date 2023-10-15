

// //-------------DTO
//
//package com.arib.NotesAppApi.dto;
//
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class testDTO {
//
//	@NotNull(groups = Group1.class)
//	@NotBlank(groups = Group2.class)
//	Integer id;
//	String title;
//	String content;
//	@Min(value = 1)
//	@NotNull
//	Integer user;
//	Boolean deleted;
//	Boolean archived;
//	Boolean pinned;
//
//	public interface Group1 {}
//
//	public interface Group2 {}
//
//
/// Main Class--------------------------
//
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//    
//	Validator validator = factory.getValidator();
//
//
//	Common dto = new Common(Integer.parseInt("1"),"1","1",1,true,true,true);
//
//    Set<ConstraintViolation<Common>> violationsGroup1 = validator.validate(dto, Group1.class);
//    
//    Set<ConstraintViolation<Common>> violationsGroup2 = validator.validate(dto, Group2.class);
//    
//    if (!violationsGroup1.isEmpty()) {
//        for (ConstraintViolation<Common> violation : violationsGroup1) {
//            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
//        }
//    }
//
//    if (!violationsGroup2.isEmpty()) {
//        for (ConstraintViolation<Common> violation : violationsGroup2) {
//            System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
//        }
//    }
//
//----------Additional
////	// Define a constructor for creating a new note
////    public Common(@NotBlank String title, String content, @Min(value = 1) @NotNull  Integer user, boolean deleted,
////            boolean archived,
////            boolean pinned) {
////        this(null, title, content, user, deleted, archived, pinned);
////    }
//
////	// Add a method to create an UpdateValidation instance
////	public void asUpdate() { if (id == null) {
////			throw new IllegalArgumentException("Note ID must be provided for updating note");}}
////	// Add a method to create an UpdateValidation instance
////	public void asSave() {if (user == null) {
////			throw new IllegalArgumentException("User ID must be provided for Saving note");}}
//
//}


