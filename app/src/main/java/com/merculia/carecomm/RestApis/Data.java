package com.merculia.carecomm.RestApis;

import com.merculia.carecomm.RestApis.Auth.SignupRequestModel;
import com.merculia.carecomm.RestApis.Contact.ContactModel;
import com.merculia.carecomm.RestApis.Contact.SearchResponseModel;
import com.merculia.carecomm.RestApis.Event.DailyEventResponseModel;
import com.merculia.carecomm.RestApis.Event.EventModel;
import com.merculia.carecomm.RestApis.Event.UpcomingEventResponseModel;
import com.merculia.carecomm.RestApis.Profile.GetInfoResponseModel;
import com.merculia.carecomm.RestApis.Profile.UserModel;

public class Data {
    public static String token = "";
    public static UserModel myUserData;

    public static ContactModel selectedContact;
    public static SearchResponseModel.User searchedUser;

    public static EventModel creatingEvent = new EventModel();

    public static DailyEventResponseModel.EventModel selectedDailyEvent = null;
    public static UpcomingEventResponseModel.EventModel selectedUpcomingEvent = null;
}
