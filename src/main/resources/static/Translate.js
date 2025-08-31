// Initialize i18next
i18next.init({
  lng: localStorage.getItem('lang') || 'en', // Default to English
  resources: {
    en: {
      translation: {
        navTitle: "Event Management",
        homeLink: "Home",
        contactLink: "Contact",
        searchTitle: "Search Events Worldwide",
        eventManagementTitle: "Event Management",
        titleHeader: "Title",
        descriptionHeader: "Description",
        startDateHeader: "DateStart",
        endDateHeader: "DateEnd",
        locationHeader: "Location",
        actionsHeader: "Actions",
        addEventTitle: "Add New Event",
        eventTitleLabel: "Event Title",
        eventDescLabel: "Event Description",
        eventStartDateLabel: "Event Date Start",
        eventEndDateLabel: "Event Date End",
        eventLocationLabel: "Event Location",
        protectedContentTitle: "Protected Page Content",
        protectedContentText: "This content is only accessible after login.",
        addEventBtn: "Add Event",
        editBtn: "Edit",
        deleteBtn: "Delete",
        inviteBtn: "Invite",
        registerBtn: "Register",
        loginBtn: "Login",
        logoutBtn: "Logout",
      }
    },
    fr: {
      translation: {
        navTitle: "Gestion des événements",
        homeLink: "Accueil",
        contactLink: "Contact",
        searchTitle: "Rechercher des événements dans le monde",
        eventManagementTitle: "Gestion des événements",
        titleHeader: "Titre",
        descriptionHeader: "Description",
        startDateHeader: "DateDébut",
        endDateHeader: "DateFin",
        locationHeader: "Emplacement",
        actionsHeader: "Actions",
        addEventTitle: "Ajouter un événement",
        eventTitleLabel: "Titre de l'événement",
        eventDescLabel: "Description de l'événement",
        eventStartDateLabel: "Date de début de l'événement",
        eventEndDateLabel: "Date de fin de l'événement",
        eventLocationLabel: "Lieu de l'événement",
        protectedContentTitle: "Contenu protégé",
        protectedContentText: "Ce contenu est accessible uniquement après connexion.",
        addEventBtn: "Ajouter un événement",
        editBtn: "Modifier",
        deleteBtn: "Supprimer",
        inviteBtn: "Inviter",
        registerBtn: "S'inscrire",
        loginBtn: "Connexion",
        logoutBtn: "Déconnexion",
      }
    },
    ar: {
      translation: {
        navTitle: "إدارة الفعاليات",
        homeLink: "الرئيسية",
        contactLink: "اتصل بنا",
        searchTitle: "ابحث عن فعاليات في جميع أنحاء العالم",
        eventManagementTitle: "إدارة الفعاليات",
        titleHeader: "العنوان",
        descriptionHeader: "الوصف",
        startDateHeader: "تاريخ البدء",
        endDateHeader: "تاريخ الانتهاء",
        locationHeader: "الموقع",
        actionsHeader: "الإجراءات",
        addEventTitle: "إضافة فعالية جديدة",
        eventTitleLabel: "عنوان الفعالية",
        eventDescLabel: "وصف الفعالية",
        eventStartDateLabel: "تاريخ بداية الفعالية",
        eventEndDateLabel: "تاريخ نهاية الفعالية",
        eventLocationLabel: "موقع الفعالية",
        protectedContentTitle: "المحتوى المحمي",
        protectedContentText: "هذا المحتوى متاح فقط بعد تسجيل الدخول.",
        addEventBtn: "إضافة فعالية",
        editBtn: "تعديل",
        deleteBtn: "حذف",
        inviteBtn: "دعوة",
        registerBtn: "تسجيل",
        loginBtn: "تسجيل الدخول",
        logoutBtn: "تسجيل الخروج",
      }
    }
  }
}, function(err, t) {
  if (err) {
    console.error('Error initializing i18next:', err);
    return;
  }

  // Update content after initialization
  updateContent();
});

// Language change event
document.getElementById('langSelect').addEventListener('change', function() {
  const selectedLang = this.value;
  i18next.changeLanguage(selectedLang, function(err, t) {
    if (err) {
      console.error('Language change failed:', err);
      return;
    }
    localStorage.setItem('lang', selectedLang);
    updateContent();
  });
});

// Function to update the content based on selected language
function updateContent() {
  document.getElementById('navTitle').textContent = i18next.t('navTitle');
  document.getElementById('homeLink').textContent = i18next.t('homeLink');
  document.getElementById('contactLink').textContent = i18next.t('contactLink');
  document.getElementById('searchTitle').textContent = i18next.t('searchTitle');
  document.getElementById('eventManagementTitle').textContent = i18next.t('eventManagementTitle');
  document.getElementById('titleHeader').textContent = i18next.t('titleHeader');
  document.getElementById('descriptionHeader').textContent = i18next.t('descriptionHeader');
  document.getElementById('startDateHeader').textContent = i18next.t('startDateHeader');
  document.getElementById('endDateHeader').textContent = i18next.t('endDateHeader');
  document.getElementById('locationHeader').textContent = i18next.t('locationHeader');
  document.getElementById('actionsHeader').textContent = i18next.t('actionsHeader');
  document.getElementById('addEventTitle').textContent = i18next.t('addEventTitle');
  document.getElementById('eventTitleLabel').textContent = i18next.t('eventTitleLabel');
  document.getElementById('eventDescLabel').textContent = i18next.t('eventDescLabel');
  document.getElementById('eventStartDateLabel').textContent = i18next.t('eventStartDateLabel');
  document.getElementById('eventEndDateLabel').textContent = i18next.t('eventEndDateLabel');
  document.getElementById('eventLocationLabel').textContent = i18next.t('eventLocationLabel');
  document.getElementById('protectedContentTitle').textContent = i18next.t('protectedContentTitle');
  document.getElementById('protectedContentText').textContent = i18next.t('protectedContentText');
  document.getElementById('deleteBtn').textContent = i18next.t('deleteBtn');
  document.getElementById('editBtn').textContent = i18next.t('editBtn');
  document.getElementById('inviteBtn').textContent = i18next.t('inviteBtn');
  document.getElementById('addEventBtn').textContent = i18next.t('addEventBtn');
  document.getElementById('registerBtn').textContent = i18next.t('registerBtn');
  document.getElementById('loginBtn').textContent = i18next.t('loginBtn');
  document.getElementById('logoutBtn').textContent = i18next.t('logoutBtn');
}
