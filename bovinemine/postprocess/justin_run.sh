#/bin/bash
#sudo systemctl restart postgresql-9.5
#sleep 4m
#ant -Daction=create-references -v
#sudo systemctl restart postgresql-9.5
#sleep 4m
#ant -Daction=transfer-sequences -v
#sudo systemctl restart postgresql-9.5
#sleep 4m

#ant -Daction=create-overlap-view -v
#sudo systemctl restart postgresql-9.5
#sleep 4m
#ant -Daction=create-location-overlap-index -v
#sudo systemctl restart postgresql-9.5
#sleep 4m
#ant -Daction=do-sources -v
#sudo systemctl restart postgresql-9.5
#sleep 4m

#ant -Daction=create-attribute-indexes -v
#sudo systemctl restart postgresql-9.5
#sleep 4m
#ant -Daction=create-search-index -v
#sudo systemctl restart postgresql-9.5
#sleep 4m
ant -Daction=summarise-objectstore -v
sudo systemctl restart postgresql-9.5
sleep 4m
ant -Daction=create-autocomplete-index -v
