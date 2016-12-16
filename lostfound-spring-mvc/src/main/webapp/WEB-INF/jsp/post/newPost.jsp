<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
         trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Create new post">

	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
	</jsp:attribute>

	<jsp:attribute name="body">

      <!-- Your Page Content Here -->

        <div class="row">
            <div class="col-md-12">
                <!-- form start -->
                <form role="form">
                    <!-- general form elements -->
                    <div class="box box-primary" id="info-about-item">
                        <div class="box-header with-border">
                            <h3 class="box-title">Info about item</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-group">
                                <label>Location</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-location-arrow"></i></span>
                                    <input type="text" class="form-control" value="Brno" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Type</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-chevron-circle-down"></i></span>
                                    <select class="form-control select2" style="width: 100%;">
                                        <option selected="selected" value="0">Lost</option>
                                        <option value="1">Found</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->

                    <div class="box box-success" id="info-actions">
                        <div class="box-header with-border">
                            <h3 class="box-title">Actions</h3>
                        </div>
                        <div class="box-body">
                            <button type="submit" id="add-item" class="btn btn-default">Add Item</button>
                            <button type="submit" class="btn btn-info">Submit</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>

                  <!-- /.box -->
          <div class="box box-success" id="item-info" style="display:none;">
              <div class="box-header with-border">
                  <h3 class="box-title">Item Info - #index#</h3>
              </div>
              <div class="box-body">
                  <div class="form-group">
                      <label>Name</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <input type="text" class="form-control" name="item-info-name[#index#][]" value="" placeholder="">
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Description</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <textarea class="form-control" rows="3" name="item-info-description[#index#][]" value="" placeholder="Write your descrition of item"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Images</label><a href="" class="add-image" data-index="" style="margin-left: 4px;"><i class="fa fa-plus-circle"></i></a>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-image"></i></span>
                          <input type="text" class="form-control" name="item-info-image[#index#][]" value="" placeholder="Set URL">
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Keywords</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <textarea class="form-control" rows="3" name="item-info-keywords[#index#][]" value="" placeholder="Keyword per line"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Categories</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-cog"></i></span>
                          <select class="form-control categories" multiple="multiple" name="item-info-categories[#index#][]" data-placeholder="Select a categories" style="width: 100%;">
                              <option>Antiques</option>
                              <option>Antiquities</option>
                              <option>Architectural & Garden</option>
                              <option>Asian Antiques</option>
                              <option>Decorative Arts</option>
                              <option>Ethnographic</option>
                              <option>Home & Hearth</option>
                              <option>Incunabula</option>
                              <option>Linens & Textiles (Pre-1930)</option>
                              <option>Manuscripts</option>
                              <option>Maps, Atlases & Globes</option>
                              <option>Maritime</option>
                              <option>Mercantile, Trades &amp; Factories</option>
                              <option>Musical Instruments (Pre-1930)</option>
                              <option>Other Antiques</option>
                              <option>Periods & Styles</option>
                              <option>Primitives</option>
                              <option>Reproduction Antiques</option>
                              <option>Restoration & Care</option>
                              <option>Rugs & Carpets</option>
                              <option>Science & Medicine (Pre-1930)</option>
                              <option>Sewing (Pre-1930)</option>
                              <option>Silver</option>
                              <option>Art</option>
                              <option>Art from Dealers & Resellers</option>
                              <option>Direct from the Artist</option>
                              <option>Baby</option>
                              <option>Baby Gear</option>
                              <option>Baby Safety & Health</option>
                              <option>Bathing & Grooming</option>
                              <option>Car Safety Seats</option>
                              <option>Carriers, Slings & Backpacks</option>
                              <option>Diapering</option>
                              <option>Feeding</option>
                              <option>Keepsakes & Baby Announcements</option>
                              <option>Nursery Bedding</option>
                              <option>Nursery Décor</option>
                              <option>Nursery Furniture</option>
                              <option>Other Baby</option>
                              <option>Potty Training</option>
                              <option>Strollers & Accessories</option>
                              <option>Toys for Baby</option>
                              <option>Books</option>
                              <option>Accessories</option>
                              <option>Antiquarian & Collectible</option>
                              <option>Audiobooks</option>
                              <option>Catalogs</option>
                              <option>Children & Young Adults</option>
                              <option>Cookbooks</option>
                              <option>Fiction & Literature</option>
                              <option>Magazine Back Issues</option>
                              <option>Nonfiction</option>
                              <option>Other Books</option>
                              <option>Textbooks, Education</option>
                              <option>Wholesale & Bulk Lots</option>
                              <option>Business & Industrial</option>
                              <option>Agriculture & Forestry</option>
                              <option>Automation, Motors & Drives</option>
                              <option>Construction</option>
                              <option>Electrical & Test Equipment</option>
                              <option>Fuel & Energy</option>
                              <option>Healthcare, Lab & Life Science</option>
                              <option>Heavy Equipment</option>
                              <option>Heavy Equipment Attachments</option>
                              <option>Heavy Equipment Parts & Accs</option>
                              <option>Light Equipment & Tools</option>
                              <option>Manufacturing & Metalworking</option>
                              <option>MRO & Industrial Supply</option>
                              <option>Office</option>
                              <option>Other Business & Industrial</option>
                              <option>Packing & Shipping</option>
                              <option>Printing & Graphic Arts</option>
                              <option>Restaurant & Catering</option>
                              <option>Retail & Services</option>
                              <option>Websites & Businesses for Sale</option>
                              <option>Cameras & Photo</option>
                              <option>Binoculars & Telescopes</option>
                              <option>Camcorders</option>
                              <option>Camera & Photo Accessories</option>
                              <option>Camera Drone Parts & Accs</option>
                              <option>Camera Drones</option>
                              <option>Camera Manuals & Guides</option>
                              <option>Digital Cameras</option>
                              <option>Digital Photo Frames</option>
                              <option>Film Photography</option>
                              <option>Flashes & Flash Accessories</option>
                              <option>Lenses & Filters</option>
                              <option>Lighting & Studio</option>
                              <option>Other Cameras & Photo</option>
                              <option>Replacement Parts & Tools</option>
                              <option>Tripods & Supports</option>
                              <option>Video Production & Editing</option>
                              <option>Vintage Movie & Photography</option>
                              <option>Cell Phones & Accessories</option>
                              <option>Cell Phone & Smartphone Parts</option>
                              <option>Cell Phone Accessories</option>
                              <option>Cell Phones & Smartphones</option>
                              <option>Display Phones</option>
                              <option>Other Cell Phones & Accs</option>
                              <option>Phone Cards & SIM Cards</option>
                              <option>Smart Watch Accessories</option>
                              <option>Smart Watches</option>
                              <option>Vintage Cell Phones</option>
                              <option>Clothing, Shoes & Accessories</option>
                              <option>Baby & Toddler Clothing</option>
                              <option>Costumes, Reenactment, Theater</option>
                              <option>Cultural & Ethnic Clothing</option>
                              <option>Dancewear</option>
                              <option>Kids' Clothing, Shoes & Accs</option>
                              <option>Men's Accessories</option>
                              <option>Men's Clothing</option>
                              <option>Men's Shoes</option>
                              <option>Uniforms & Work Clothing</option>
                              <option>Unisex Clothing, Shoes & Accs</option>
                              <option>Vintage</option>
                              <option>Wedding & Formal Occasion</option>
                              <option>Wholesale, Large & Small Lots</option>
                              <option>Women's Accessories</option>
                              <option>Women's Clothing</option>
                              <option>Women's Handbags & Bags</option>
                              <option>Women's Shoes</option>
                              <option>Coins & Paper Money</option>
                              <option>Bullion</option>
                              <option>Coins: Ancient</option>
                              <option>Coins: Canada</option>
                              <option>Coins: Medieval</option>
                              <option>Coins: US</option>
                              <option>Coins: World</option>
                              <option>Exonumia</option>
                              <option>Other Coins & Paper Money</option>
                              <option>Paper Money: US</option>
                              <option>Paper Money: World</option>
                              <option>Publications & Supplies</option>
                              <option>Stocks & Bonds, Scripophily</option>
                              <option>Virtual Currency</option>
                              <option>Collectibles</option>
                              <option>Advertising</option>
                              <option>Animals</option>
                              <option>Animation Art & Characters</option>
                              <option>Arcade, Jukeboxes & Pinball</option>
                              <option>Autographs</option>
                              <option>Banks, Registers & Vending</option>
                              <option>Barware</option>
                              <option>Beads</option>
                              <option>Bottles & Insulators</option>
                              <option>Breweriana, Beer</option>
                              <option>Casino</option>
                              <option>Clocks</option>
                              <option>Comics</option>
                              <option>Credit, Charge Cards</option>
                              <option>Cultures & Ethnicities</option>
                              <option>Decorative Collectibles</option>
                              <option>Disneyana</option>
                              <option>Fantasy, Mythical & Magic</option>
                              <option>Historical Memorabilia</option>
                              <option>Holiday & Seasonal</option>
                              <option>Kitchen & Home</option>
                              <option>Knives, Swords & Blades</option>
                              <option>Lamps, Lighting</option>
                              <option>Linens & Textiles (1930-Now)</option>
                              <option>Metalware</option>
                              <option>Militaria</option>
                              <option>Non-Sport Trading Cards</option>
                              <option>Paper</option>
                              <option>Pens & Writing Instruments</option>
                              <option>Pez, Keychains, Promo Glasses</option>
                              <option>Phone Cards</option>
                              <option>Photographic Images</option>
                              <option>Pinbacks, Bobbles, Lunchboxes</option>
                              <option>Postcards</option>
                              <option>Radio, Phonograph, TV, Phone</option>
                              <option>Religion & Spirituality</option>
                              <option>Rocks, Fossils & Minerals</option>
                              <option>Science & Medicine (1930-Now)</option>
                              <option>Science Fiction & Horror</option>
                              <option>Sewing (1930-Now)</option>
                              <option>Souvenirs & Travel Memorabilia</option>
                              <option>Tobacciana</option>
                              <option>Tools, Hardware & Locks</option>
                              <option>Transportation</option>
                              <option>Vanity, Perfume & Shaving</option>
                              <option>Vintage, Retro, Mid-Century</option>
                              <option>Computers/Tablets & Networking</option>
                              <option>3D Printers & Supplies</option>
                              <option>Computer Cables & Connectors</option>
                              <option>Computer Components & Parts</option>
                              <option>Desktops & All-In-Ones</option>
                              <option>Drives, Storage & Blank Media</option>
                              <option>Enterprise Networking, Servers</option>
                              <option>Home Networking & Connectivity</option>
                              <option>Keyboards, Mice & Pointers</option>
                              <option>Laptop & Desktop Accessories</option>
                              <option>Laptops & Netbooks</option>
                              <option>Manuals & Resources</option>
                              <option>Monitors, Projectors & Accs</option>
                              <option>Other Computers & Networking</option>
                              <option>Power Protection, Distribution</option>
                              <option>Printers, Scanners & Supplies</option>
                              <option>Software</option>
                              <option>Tablet & eBook Reader Accs</option>
                              <option>Tablet & eBook Reader Parts</option>
                              <option>Tablets & eBook Readers</option>
                              <option>Vintage Computing</option>
                              <option>Consumer Electronics</option>
                              <option>Gadgets & Other Electronics</option>
                              <option>Home Automation</option>
                              <option>Home Surveillance</option>
                              <option>Home Telephones & Accessories</option>
                              <option>Multipurpose Batteries & Power</option>
                              <option>Portable Audio & Headphones</option>
                              <option>Radio Communication</option>
                              <option>TV, Video & Home Audio</option>
                              <option>Vehicle Electronics & GPS</option>
                              <option>Vintage Electronics</option>
                              <option>Virtual Reality</option>
                              <option>Crafts</option>
                              <option>Art Supplies</option>
                              <option>Beads & Jewelry Making</option>
                              <option>Fabric</option>
                              <option>Fabric Painting & Decorating</option>
                              <option>Glass & Mosaics</option>
                              <option>Handcrafted & Finished Pieces</option>
                              <option>Home Arts & Crafts</option>
                              <option>Kids' Crafts</option>
                              <option>Leathercrafts</option>
                              <option>Multi-Purpose Craft Supplies</option>
                              <option>Needlecrafts & Yarn</option>
                              <option>Other Crafts</option>
                              <option>Scrapbooking & Paper Crafts</option>
                              <option>Sculpting, Molding & Ceramics</option>
                              <option>Sewing</option>
                              <option>Stamping & Embossing</option>
                              <option>Dolls & Bears</option>
                              <option>Bear Making Supplies</option>
                              <option>Bears</option>
                              <option>Dollhouse Miniatures</option>
                              <option>Dolls</option>
                              <option>Paper Dolls</option>
                              <option>DVDs & Movies</option>
                              <option>DVDs & Blu-ray Discs</option>
                              <option>Film Stock</option>
                              <option>Laserdiscs</option>
                              <option>UMDs</option>
                              <option>VHS Tapes</option>
                              <option>eBay Motors</option>
                              <option>Boats</option>
                              <option>Cars & Trucks</option>
                              <option>Motorcycles</option>
                              <option>Other Vehicles & Trailers</option>
                              <option>Parts & Accessories</option>
                              <option>Powersports</option>
                              <option>Entertainment Memorabilia</option>
                              <option>Movie Memorabilia</option>
                              <option>Music Memorabilia</option>
                              <option>Other Entertainment Mem</option>
                              <option>Television Memorabilia</option>
                              <option>Theater Memorabilia</option>
                              <option>Video Game Memorabilia</option>
                              <option>Gift Cards & Coupons</option>
                              <option>Coupons</option>
                              <option>Digital Gifts</option>
                              <option>eBay Gift Cards</option>
                              <option>Gift Cards</option>
                              <option>Gift Certificates</option>
                              <option>Health & Beauty</option>
                              <option>Bath & Body</option>
                              <option>Fragrances</option>
                              <option>Hair Care & Styling</option>
                              <option>Health Care</option>
                              <option>Makeup</option>
                              <option>Massage</option>
                              <option>Medical, Mobility & Disability</option>
                              <option>Nail Care, Manicure & Pedicure</option>
                              <option>Natural & Alternative Remedies</option>
                              <option>Oral Care</option>
                              <option>Other Health & Beauty</option>
                              <option>Salon & Spa Equipment</option>
                              <option>Shaving & Hair Removal</option>
                              <option>Skin Care</option>
                              <option>Sun Protection & Tanning</option>
                              <option>Tattoos & Body Art</option>
                              <option>Vision Care</option>
                              <option>Vitamins & Dietary Supplements</option>
                              <option>Home & Garden</option>
                              <option>Bath</option>
                              <option>Bedding</option>
                              <option>Food & Beverages</option>
                              <option>Fresh Cut Flowers & Supplies</option>
                              <option>Furniture</option>
                              <option>Greeting Cards & Party Supply</option>
                              <option>Holiday & Seasonal Décor</option>
                              <option>Home Décor</option>
                              <option>Home Improvement</option>
                              <option>Household Supplies & Cleaning</option>
                              <option>Kids & Teens at Home</option>
                              <option>Kitchen, Dining & Bar</option>
                              <option>Lamps, Lighting & Ceiling Fans</option>
                              <option>Major Appliances</option>
                              <option>Other Home & Garden</option>
                              <option>Tools</option>
                              <option>Wedding Supplies</option>
                              <option>Window Treatments & Hardware</option>
                              <option>Yard, Garden & Outdoor Living</option>
                              <option>Jewelry & Watches</option>
                              <option>Children's Jewelry</option>
                              <option>Engagement & Wedding</option>
                              <option>Ethnic, Regional & Tribal</option>
                              <option>Fashion Jewelry</option>
                              <option>Fine Jewelry</option>
                              <option>Handcrafted, Artisan Jewelry</option>
                              <option>Jewelry Boxes & Organizers</option>
                              <option>Jewelry Design & Repair</option>
                              <option>Loose Beads</option>
                              <option>Loose Diamonds & Gemstones</option>
                              <option>Men's Jewelry</option>
                              <option>Other Jewelry & Watches</option>
                              <option>Vintage & Antique Jewelry</option>
                              <option>Watches, Parts & Accessories</option>
                              <option>Music</option>
                              <option>Cassettes</option>
                              <option>CDs</option>
                              <option>Other Formats</option>
                              <option>Records</option>
                              <option>Storage & Media Accessories</option>
                              <option>Musical Instruments & Gear</option>
                              <option>Brass</option>
                              <option>DJ Equipment</option>
                              <option>Equipment</option>
                              <option>Guitars & Basses</option>
                              <option>Instruction Books, CDs & Video</option>
                              <option>Karaoke Entertainment</option>
                              <option>Other Musical Instruments</option>
                              <option>Percussion</option>
                              <option>Pianos, Keyboards & Organs</option>
                              <option>Pro Audio Equipment</option>
                              <option>Sheet Music & Song Books</option>
                              <option>Stage Lighting & Effects</option>
                              <option>String</option>
                              <option>Vintage Musical Instruments</option>
                              <option>Wind & Woodwind</option>
                              <option>Pet Supplies</option>
                              <option>Backyard Poultry Supplies</option>
                              <option>Bird Supplies</option>
                              <option>Cat Supplies</option>
                              <option>Dog Supplies</option>
                              <option>Fish & Aquariums</option>
                              <option>Other Pet Supplies</option>
                              <option>Pet Memorials & Urns</option>
                              <option>Reptile Supplies</option>
                              <option>Small Animal Supplies</option>
                              <option>Wholesale Lots</option>
                              <option>Pottery & Glass</option>
                              <option>Glass</option>
                              <option>Pottery & China</option>
                              <option>Real Estate</option>
                              <option>Commercial</option>
                              <option>Land</option>
                              <option>Manufactured Homes</option>
                              <option>Other Real Estate</option>
                              <option>Residential</option>
                              <option>Timeshares for Sale</option>
                              <option>Specialty Services</option>
                              <option>Artistic Services</option>
                              <option>Custom Clothing & Jewelry</option>
                              <option>eBay Auction Services</option>
                              <option>Graphic & Logo Design</option>
                              <option>Home Improvement Services</option>
                              <option>Item Based Services</option>
                              <option>Media Editing & Duplication</option>
                              <option>Other Specialty Services</option>
                              <option>Printing & Personalization</option>
                              <option>Restoration & Repair</option>
                              <option>Web & Computer Services</option>
                              <option>Sporting Goods</option>
                              <option>Boxing, Martial Arts & MMA</option>
                              <option>Cycling</option>
                              <option>Fishing</option>
                              <option>Fitness, Running & Yoga</option>
                              <option>Golf</option>
                              <option>Hunting</option>
                              <option>Indoor Games</option>
                              <option>Other Sporting Goods</option>
                              <option>Outdoor Sports</option>
                              <option>Team Sports</option>
                              <option>Tennis & Racquet Sports</option>
                              <option>Water Sports</option>
                              <option>Winter Sports</option>
                              <option>Sports Mem, Cards & Fan Shop</option>
                              <option>Autographs-Original</option>
                              <option>Autographs-Reprints</option>
                              <option>Fan Apparel & Souvenirs</option>
                              <option>Game Used Memorabilia</option>
                              <option>Sports Stickers, Sets & Albums</option>
                              <option>Sports Trading Cards</option>
                              <option>Vintage Sports Memorabilia</option>
                              <option>Stamps</option>
                              <option>Africa</option>
                              <option>Asia</option>
                              <option>Australia & Oceania</option>
                              <option>British Colonies & Territories</option>
                              <option>Canada</option>
                              <option>Caribbean</option>
                              <option>Europe</option>
                              <option>Great Britain</option>
                              <option>Latin America</option>
                              <option>Middle East</option>
                              <option>Other Stamps</option>
                              <option>Specialty Philately</option>
                              <option>Topical Stamps</option>
                              <option>United States</option>
                              <option>Worldwide</option>
                              <option>Tickets & Experiences</option>
                              <option>Concert Tickets</option>
                              <option>Other Tickets & Experiences</option>
                              <option>Parking Passes</option>
                              <option>Special Experiences</option>
                              <option>Sports Tickets</option>
                              <option>Theater Tickets</option>
                              <option>Theme Park & Club Passes</option>
                              <option>Toys & Hobbies</option>
                              <option>Action Figures</option>
                              <option>Beanbag Plush</option>
                              <option>Building Toys</option>
                              <option>Classic Toys</option>
                              <option>Collectible Card Games</option>
                              <option>Diecast & Toy Vehicles</option>
                              <option>Educational</option>
                              <option>Electronic, Battery & Wind-Up</option>
                              <option>Fast Food & Cereal Premiums</option>
                              <option>Games</option>
                              <option>Marbles</option>
                              <option>Model Railroads & Trains</option>
                              <option>Models & Kits</option>
                              <option>Outdoor Toys & Structures</option>
                              <option>Preschool Toys & Pretend Play</option>
                              <option>Puzzles</option>
                              <option>Radio Control & Control Line</option>
                              <option>Robots, Monsters & Space Toys</option>
                              <option>Slot Cars</option>
                              <option>Stuffed Animals</option>
                              <option>Toy Soldiers</option>
                              <option>TV, Movie & Character Toys</option>
                              <option>Vintage & Antique Toys</option>
                              <option>Travel</option>
                              <option>Airline</option>
                              <option>Campground & RV Parks</option>
                              <option>Car Rental</option>
                              <option>Cruises</option>
                              <option>Lodging</option>
                              <option>Luggage</option>
                              <option>Luggage Accessories</option>
                              <option>Maps</option>
                              <option>Other Travel</option>
                              <option>Rail</option>
                              <option>Travel Accessories</option>
                              <option>Vacation Packages</option>
                              <option>Vintage Luggage & Travel Accs</option>
                              <option>Video Games & Consoles</option>
                              <option>Manuals, Inserts & Box Art</option>
                              <option>Original Game Cases & Boxes</option>
                              <option>Other Video Games & Consoles</option>
                              <option>Prepaid Gaming Cards</option>
                              <option>Strategy Guides & Cheats</option>
                              <option>Video Game Accessories</option>
                              <option>Video Game Consoles</option>
                              <option>Video Game Merchandise</option>
                              <option>Video Games</option>
                              <option>Everything Else</option>
                              <option>Adult Only</option>
                              <option>Career Development & Education</option>
                              <option>eBay Special Offers</option>
                              <option>eBay User Tools</option>
                              <option>Every Other Thing</option>
                              <option>Funeral & Cemetery</option>
                              <option>Genealogy</option>
                              <option>Information Products</option>
                              <option>Metaphysical</option>
                              <option>Personal Development</option>
                              <option>Personal Security</option>
                              <option>Religious Products & Supplies</option>
                              <option>Reward Points & Incentives</option>
                              <option>Test Auctions</option>
                              <option>Weird Stuff</option>
                          </select>
                      </div>
                  </div>
              </div>
          </div>

          <div class="input-group" id="add-image" style="display: none;">
              <span class="input-group-addon"><a href="#" class="image-remove"><i class="fa fa-minus-circle"></i></a></span>
              <input type="text" class="form-control" name="item-info-image[#index#][]" value="" placeholder="Set URL">
          </div>


	</jsp:attribute>

    	<jsp:attribute name="footer">
		<!-- Select2 -->
		<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
		<script type="text/javascript">
            $(function()
            {
                $(".select2").select2();

                var iterate = 0;
                var itemCopy = $("#item-info");
                var infoItem = $("#info-about-item");
                var infoActions = $("#info-actions");
                var elAddImage = $("#add-image");

                //INIT
                setValueOptions();
                addItem();

                function addItem()
                {
                    var copy = itemCopy.clone();

                    infoActions.before(copy);
                    copy.attr('id', 'categories-' + iterate);
                    copy.show();
                    $("#categories-" + iterate + " select.categories").select2();
                    replaceIndex(copy, iterate);
                    iterate++;
                }

                function replaceIndex(el, index)
                {
                    var title = el.find('h3');
                    var link = el.find('.add-image');

                    title.html(title.html().replace('#index#', index+1));
                    link.data('index', index);

                    el.find('.form-control').each(function()
                    {
                        var child = $(this);
                        child.attr('name', child.attr('name').replace('#index#', index));
                    });
                }

                function setValueOptions()
                {
                    $("select.categories option").each(function()
                    {
                        var el = $(this);
                        el.val(el.text());
                    });
                }

                function addImage(linkButton)
                {
                    var el = linkButton.next();
                    var clone = elAddImage.clone();
                    var input = clone.find('.form-control');
                    var index = linkButton.data('index');

                    el.parent().append(clone);

                    input.each(function()
                    {
                        var child = $(this);
                        child.attr('name', child.attr('name').replace('#index#', index));
                    });

                    clone.attr('id', '').show();
                }

                $("#add-item").click(function(e)
                {
                    e.preventDefault();
                    addItem();
                });

                $('body').delegate('.add-image', 'click', function(event)
                {
                    event.preventDefault();
                    addImage($(this));
                });

                $('body').delegate('.image-remove', 'click', function(event) {
                    event.preventDefault();
                    $(this).parent().parent().remove();
                })

            });
        </script>
	</jsp:attribute>
</my:pagetemplate>
