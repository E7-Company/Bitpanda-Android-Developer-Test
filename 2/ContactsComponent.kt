class ContactsComponent {
    /**
     * in the live code this is set after the view loaded; you can assume that this won't be null or empty
     * you can mock or change this if you want
     */
    var contacts: List<Contact>? = null

    fun setContacts() {
        contacts = listOf(
            Contact("1", "A", Time("111", "111")),
            Contact("2", "B", Time("333", "333")),
            Contact("3", "A", Time("222", "222")),
            Contact("4", "C", Time("555", "555")),
            Contact("5", "C", Time("666", "666")),
            Contact("6", "A", Time("444", "444"))
        )
    }

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    fun getRecentContacts(): List<Contact>? {
        return contacts?.sortedByDescending { it.last_used.unix }?.distinctBy { it.data }?.take(3)
    }
}