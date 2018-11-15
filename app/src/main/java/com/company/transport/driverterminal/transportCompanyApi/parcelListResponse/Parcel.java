package com.company.transport.driverterminal.transportCompanyApi.parcelListResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parcel {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("width")
    @Expose
    public String width;
    @SerializedName("length")
    @Expose
    public String length;
    @SerializedName("weight")
    @Expose
    public String weight;
    @SerializedName("delivery_type")
    @Expose
    public String deliveryType;
    @SerializedName("cost")
    @Expose
    public String cost;
    @SerializedName("location_address")
    @Expose
    public String locationAddress;
    @SerializedName("location_latitude")
    @Expose
    public String locationLatitude;
    @SerializedName("location_longitude")
    @Expose
    public String locationLongitude;
    @SerializedName("origin_address")
    @Expose
    public String originAddress;
    @SerializedName("origin_latitude")
    @Expose
    public String originLatitude;
    @SerializedName("origin_longitude")
    @Expose
    public String originLongitude;
    @SerializedName("destination_address")
    @Expose
    public String destinationAddress;
    @SerializedName("destination_latitude")
    @Expose
    public String destinationLatitude;
    @SerializedName("destination_longitude")
    @Expose
    public String destinationLongitude;
    @SerializedName("insurance")
    @Expose
    public Integer insurance;
    @SerializedName("priority")
    @Expose
    public Integer priority;
    @SerializedName("delivery_time")
    @Expose
    public String deliveryTime;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    public Object deletedAt;
    @SerializedName("courier_id")
    @Expose
    public Integer courierId;
    @SerializedName("track")
    @Expose
    public String track;

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getLength() {
        return length;
    }

    public String getWeight() {
        return weight;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getCost() {
        return cost;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getOriginLatitude() {
        return originLatitude;
    }

    public String getOriginLongitude() {
        return originLongitude;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public String getTrack() {
        return track;
    }
}
