# AES_CBC_Decryption
Padding oracle decrypting a message in hex

Σκοπός της συγκεκριμένης εργασίας είναι να επιτευχθεί η σωστή εφαρμογή των θεμελιακών στοιχείων της κρυπτογραφίας. Το σενάριο απαιτεί την επίθεση padding oracle σε AES-CBS έτσι ώστε να αποκαλυφθεί το αρχικό κείμενο (plaintext) μέσα από το κρυπτοκείμενο (ciphertext) και μετά να το αποκρυπτογραφήσουμε εξασφαλίζοντας την αυθεντικότητά του. Δεδομένου του URL της εκφώνησης πραγματοποιήσαμε επίθεση padding σε κάθε ένα απο τα 4 μπλοκ δεδομένων (4x16=128) που προκύπτουν ούτως ώστε να εμφανισθεί το κρυπτοκείμενο.
