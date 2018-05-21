using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Version.Upgrade.Check.RNVersionUpgradeCheck
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNVersionUpgradeCheckModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNVersionUpgradeCheckModule"/>.
        /// </summary>
        internal RNVersionUpgradeCheckModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNVersionUpgradeCheck";
            }
        }
    }
}
