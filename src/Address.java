public class Address
    {
        private int addressID;
        private String street;
        private int zip;
        private String city;

        public Address()
        {
        }

        public Address(int addressID, String street, int zip, String city)
        {
            this.addressID = addressID;
            this.street = street;
            this.zip = zip;
            this.city = city;
        }

        public int getAddressID()
        {
            return addressID;
        }

        public String getStreet()
        {
            return street;
        }

        public int getZip()
        {
            return zip;
        }

        public String getCity()
        {
            return city;
        }
    }
