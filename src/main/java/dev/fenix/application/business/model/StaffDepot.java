package dev.fenix.application.business.model;

import dev.fenix.application.production.logistic.model.Depot;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "bz__staff_depots")
public class StaffDepot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "depot_id")
    private Depot depot;


    @Enumerated(EnumType.STRING)
    @Column(name = "depot_staff_type")
    private DepotStaffType depotStaffType;


    public JSONObject toJson() {
        JSONObject depotJSON = new JSONObject();
        try {
            depotJSON.put("id", depot.getId());
            depotJSON.put("name", depot.getName());
            depotJSON.put("description", depot.getDescription());
            depotJSON.put("code", depot.getCode());
            depotJSON.put("address", depot.getAddress());
            depotJSON.put("active", depot.getActive());
            depotJSON.put("createDate", depot.getCreateDate());
            depotJSON.put("modifyDate", depot.getModifyDate());
            depotJSON.put("depotStaffType", this.depotStaffType.toString() );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return depotJSON;
    }

}

