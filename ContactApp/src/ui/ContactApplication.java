package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import business.Contact;
import business.TestContact;

public class ContactApplication {
	public static void main(String[] args) {
		System.out.println("Welcome to the Contact Application");

		List<Contact> contacts = new ArrayList<>();

		contacts.add(new Contact("Mike Murach", null, "800-221-5528"));
		contacts.add(new Contact("Anne Boehm", null, null));
		contacts.add(new Contact("Joel Murach", "joel@murach.com", null));

		// No Lambda Which contacts have no email address?
		List<Contact> contactsWithoutEmail = new ArrayList<>();
		for (Contact c : contacts) {
			if (c.getEmail() == null) {
				contactsWithoutEmail.add(c);
			}
		}

		// No Lambda Which contacts have no phone?
		List<Contact> contactsWithoutPhone = new ArrayList<>();
		for (Contact c : contacts) {
			if (c.getPhone() == null) {
				contactsWithoutPhone.add(c);
			}
		}

		System.out.println("(No Lambda)" + contactsWithoutEmail);
		System.out.println("(No Lambda)" + contactsWithoutPhone);

		System.out.println("Lambdas");

		System.out.println("Contacts w/ no phone:");
		contactsWithoutPhone = filterContacts(contacts, c -> c.getPhone() == null);
		System.out.println(contactsWithoutPhone);

		System.out.println("Contacts w/ no email:");
		contactsWithoutEmail = filterContacts(contacts, c -> c.getEmail() == null);
		System.out.println(contactsWithoutEmail);

		System.out.println("\nStreams");

		contacts.stream()
			.filter(c -> c.getPhone() == null)
			.forEach(c -> System.out.println(c.getName()));
		
		List<Contact> contactsNoEmail = contacts.stream().filter(c -> c.getEmail() == null).collect(Collectors.toList());
		contactsNoEmail.stream().forEach(c -> System.out.println(c.getName() + " " + c.getPhone()));
		
		List<String> contactName = contacts.stream().map(c -> c.getName()).collect(Collectors.toList());
		contactName.stream().forEach(c -> System.out.println(c));
		
		String csv = contacts.stream().map(c -> c.getName()).reduce("", (a, b) -> a + b + ", ");
		csv = csv.substring(0,csv.length()-2);
		System.out.println(csv);
		
		System.out.println("Goodbye");
	}

	private static List<Contact> filterContacts(List<Contact> contacts, TestContact condition) {

		List<Contact> filteredContacts = new ArrayList<>();
		for (Contact c : contacts) {
			if (condition.test(c)) {
				filteredContacts.add(c);
			}

		}
		return filteredContacts;
	}
}
